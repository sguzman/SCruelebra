package com.github.sguzman.scala.sculebra

trait Add[A] extends Drawable {
  def +(tup: (Int, Int)): A
}
