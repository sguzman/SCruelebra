package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html.Canvas

object Main {
  case class Point(x: Int, y: Int){
    def +(p: Point) = Point(x + p.x, y + p.y)
    def /(d: Int) = Point(x / d, y / d)
  }

  def main(args: Array[String]): Unit = {
    val canvas = document.getElementById("canvas").asInstanceOf[Canvas]
    val ctx = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    ctx.fillStyle = "red"
    ctx.fillRect(0, 0, canvas.width, canvas.height)
  }
}
