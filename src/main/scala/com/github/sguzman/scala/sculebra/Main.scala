package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.KeyboardEvent

object Main {
  def main(args: Array[String]): Unit = {
    val canvas = document.getElementById("canvas").asInstanceOf[Canvas]
    val ctx = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = dom.window.innerWidth.toInt
    canvas.height = dom.window.innerHeight.toInt

    ctx.fillStyle = "black"
    ctx.fillRect(0, 0, canvas.width, canvas.height)

    ctx.fillStyle = "red"
    ctx.fillRect(canvas.width / 2, canvas.height / 2, 200, 200)


    document.onkeypress = (e: KeyboardEvent) => {
      println(e.charCode)
    }
  }
}
