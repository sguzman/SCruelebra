package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.KeyboardEvent
import org.scalajs.dom.{CanvasRenderingContext2D, document}

object Main {
  def main(args: Array[String]): Unit = {
    implicit val canvas: Canvas = document.getElementById("canvas").asInstanceOf[Canvas]
    implicit val ctx: CanvasRenderingContext2D = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]
    val radius: Int = 10

    canvas.width = dom.window.innerWidth.toInt
    canvas.height = dom.window.innerHeight.toInt

    ctx.fillStyle = "black"
    ctx.fillRect(0, 0, canvas.width, canvas.height)

    var food = Square(util.Random.nextInt(canvas.height), util.Random.nextInt(canvas.width), radius, canvas)

    var snaek = List(Square(canvas.width / 2, canvas.height / 2, radius, canvas))
    var direction = (0, -1)

    document.onkeypress = (e: KeyboardEvent) => e.charCode match {
      case 97 => direction = (-1, 0)
      case 100 => direction = (1, 0)
      case 115 => direction = (0, 1)
      case 119 => direction = (0, -1)
    }

    dom.window.setInterval(() => {
      ctx.fillStyle = "black"
      ctx.fillRect(0, 0, canvas.width, canvas.height)

      food.draw

      snaek = snaek.map(_ + direction)
      snaek.foreach(_.draw)
    }, 50)
  }
}
