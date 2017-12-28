package com.github.sguzman.scala.sculebra

import org.scalajs.dom.html.Canvas

case class Square(
                   x: Int,
                   y: Int,
                   radius: Int,
                   canvas: Canvas
                 ) extends DrawableSnakePiece {
  def +(tup: (Int, Int)): Square =
    if (this.x <= 0 && tup == (-1, 0))
      Square(canvas.width - 1, (this.y + tup._2 * this.radius) % canvas.height, this.radius, this.canvas)
    else if (this.y <= 0 && tup == (0, -1))
      Square((this.x + tup._1 * this.radius) % canvas.width, canvas.height - 1, this.radius, this.canvas)
    else
      Square((this.x + tup._1 * this.radius) % canvas.width, (this.y + tup._2 * this.radius) % canvas.height, this.radius, this.canvas)
}
