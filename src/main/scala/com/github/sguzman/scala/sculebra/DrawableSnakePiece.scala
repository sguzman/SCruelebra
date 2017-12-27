package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

trait DrawableSnakePiece extends Drawable {
  val radius = 10

  override def draw(implicit ctx: dom.CanvasRenderingContext2D, canvas: Canvas): Unit = {
    ctx.fillStyle = "red"
    ctx.fillRect(this.x, this.y, 10, 10)
  }
}
