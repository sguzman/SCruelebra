package com.github.sguzman.scala.sculebra

case class Square(
                   x: Int,
                   y: Int,
                   override val radius: Int = 10
                 ) extends DrawableSnakePiece with Add[Square] {
  override def +(tup: (Int, Int)): Square =
    Square(this.x + tup._1 * this.radius, this.y + tup._2 * this.radius)
}
