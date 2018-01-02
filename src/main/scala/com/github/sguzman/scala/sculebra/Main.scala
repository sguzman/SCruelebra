package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.KeyboardEvent
import org.scalajs.dom.{CanvasRenderingContext2D, document}
import rxscalajs.Observable

import scala.util.Random

object Main {
  final case class Circle(var x: Int, var y: Int, color: String = "red", radius: Int = 10) {
    def >:<(that: Circle): Boolean = math.sqrt(math.pow(this.x - that.x, 2) + math.pow(this.y - that.y, 2)) < this.radius
    def +(tup: List[Int]): Circle = Circle(this.x + (tup.head * (this.radius * 2)), this.y + (tup.last * (this.radius * 2)), this.color, this.radius)
    def draw(ctx: CanvasRenderingContext2D): Unit = {
      ctx.beginPath
      ctx.arc(this.x, this.y, this.radius, 0, 2 * Math.PI)
      ctx.fillStyle = this.color
      ctx.fill()
    }
  }

  def main(args: Array[String]): Unit = {
    val canvas: Canvas = document.getElementById("canvas").asInstanceOf[Canvas]
    canvas.width = dom.window.innerWidth.toInt
    canvas.height = dom.window.innerHeight.toInt

    val ctx: CanvasRenderingContext2D = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    Random.setSeed(System.currentTimeMillis())

    val waitTimeBetweenFrames = 250

    val input$ = Observable.fromEvent(document.body, "keypress")
      .map(_.asInstanceOf[KeyboardEvent])
      .map(_.charCode)

    var direction = List(-1, 0)
    var snaek = Circle(canvas.width / 2, canvas.height / 2)

    var food = Circle(Random.nextInt(canvas.width), Random.nextInt(canvas.height),  "black")

    val left$ = input$.filter(_ == 97).mapTo(List(-1, 0))
    val right$ = input$.filter(_ == 100).mapTo(List(1, 0))
    val up$ = input$.filter(_ == 119).mapTo(List(0, -1))
    val down$ = input$.filter(_ == 115).mapTo(List(0, 1))

    val inputs$ = left$.merge(right$).merge(up$).merge(down$).distinctUntilChanged
    inputs$.subscribe(s => direction = s)

    val gameLoop$ = Observable.interval(waitTimeBetweenFrames)

    gameLoop$.filter(_ => snaek.x > canvas.width).subscribe(_ => snaek.x = 0)
    gameLoop$.filter(_ => snaek.x < 0).subscribe(_ => snaek.x = canvas.width)
    gameLoop$.filter(_ => snaek.y > canvas.height).subscribe(_ => snaek.y = 0)
    gameLoop$.filter(_ => snaek.y < 0).subscribe(_ => snaek.y = canvas.height)

    gameLoop$.subscribe(s => {
      ctx.clearRect(0, 0, canvas.width, canvas.height)

      snaek = snaek + direction
      snaek.draw(ctx)
      food.draw(ctx)
    })
  }
}
