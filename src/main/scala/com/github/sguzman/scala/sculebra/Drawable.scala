package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

trait Drawable {
  val x: Int
  val y: Int

  def draw(implicit ctx: dom.CanvasRenderingContext2D, canvas: Canvas): Unit
}
