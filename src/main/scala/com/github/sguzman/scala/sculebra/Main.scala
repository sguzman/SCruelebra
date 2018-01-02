package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.KeyboardEvent
import org.scalajs.dom.{CanvasRenderingContext2D, document}
import rxscalajs.Observable

object Main {
  final case class Circle(x: Int, y: Int, radius: Int = 10) {
    def >:<(that: Circle): Boolean = math.sqrt(math.pow(this.x - that.x, 2) + math.pow(this.y - that.y, 2)) < this.radius
  }

  def main(args: Array[String]): Unit = {
    val canvas: Canvas = document.getElementById("canvas").asInstanceOf[Canvas]
    canvas.width = dom.window.innerWidth.toInt
    canvas.height = dom.window.innerHeight.toInt

    val ctx: CanvasRenderingContext2D = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    val waitTimeBetweenFrames = 125

    val input$ = Observable.fromEvent(document.body, "keypress")
      .map(_.asInstanceOf[KeyboardEvent])
      .map(_.charCode)

    var direction = (-1, 0)
    var snaek = List(Circle(canvas.width / 2, canvas.height / 2))

    val left$ = input$.filter(_ == 97).mapTo(-1, 0)
    val right$ = input$.filter(_ == 100).mapTo(1, 0)
    val up$ = input$.filter(_ == 119).mapTo(0, -1)
    val down$ = input$.filter(_ == 115).mapTo(0, 1)

    val inputs$ = left$.merge(right$).merge(up$).merge(down$).distinctUntilChanged
    inputs$.subscribe(s => direction = s)

    val gameLoop$ = Observable.interval(waitTimeBetweenFrames)

    gameLoop$.subscribe(s => {
      ctx.beginPath
      ctx.arc(snaek.head.x, snaek.head.y, snaek.head.radius, 0, 2 * Math.PI)
      ctx.fillStyle = "red"
      ctx.fill()
    })
  }
}
