package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.{CanvasRenderingContext2D, document}

object Main {
  def main(args: Array[String]): Unit = {
    implicit val canvas: Canvas = document.getElementById("canvas").asInstanceOf[Canvas]
    implicit val ctx: CanvasRenderingContext2D = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = dom.window.innerWidth.toInt
    canvas.height = dom.window.innerHeight.toInt

    ctx.fillStyle = "black"
    ctx.fillRect(0, 0, canvas.width, canvas.height)

    var snaek = List(Square(canvas.width / 2, canvas.height / 2))
    var direction = (0, -1)

    dom.window.setInterval(() => {
      ctx.fillStyle = "black"
      ctx.fillRect(0, 0, canvas.width, canvas.height)

      snaek = snaek.map(_ + direction)
      snaek.foreach(_.draw)
    }, 50)
  }
}
