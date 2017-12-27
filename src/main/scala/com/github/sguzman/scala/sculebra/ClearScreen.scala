package com.github.sguzman.scala.sculebra

import org.scalajs.dom.CanvasRenderingContext2D
import org.scalajs.dom.html.Canvas

trait ClearScreen extends Drawable{
  override def draw(implicit ctx: CanvasRenderingContext2D, canvas: Canvas): Unit = {
    ctx.fillStyle = "black"
    ctx.fillRect(0, 0, canvas.width, canvas.height)
  }
}
