package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

trait DrawableSnakePiece extends Drawable {
  override def draw(implicit ctx: dom.CanvasRenderingContext2D, canvas: Canvas): Unit = {
    ctx.fillStyle = "red"
    ctx.fillRect(this.x, this.y, this.radius, this.radius)
  }
}
