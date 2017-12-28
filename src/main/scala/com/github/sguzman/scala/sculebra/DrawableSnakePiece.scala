package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

trait DrawableSnakePiece extends Drawable {
  var color = "red"

  override def draw(implicit ctx: dom.CanvasRenderingContext2D, canvas: Canvas): Unit = {
    ctx.beginPath
    ctx.arc(this.x, this.y, this.radius, 0, 2 * Math.PI)
    ctx.stroke()
    ctx.fillStyle = this.color
    ctx.fill()
  }
}
