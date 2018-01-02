package com.github.sguzman.scala.sculebra

import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.KeyboardEvent
import org.scalajs.dom.{CanvasRenderingContext2D, document}
import rxscalajs.Observable

object Main {
  def main(args: Array[String]): Unit = {
    val canvas: Canvas = document.getElementById("canvas").asInstanceOf[Canvas]
    canvas.width = dom.window.innerWidth.toInt
    canvas.height = dom.window.innerHeight.toInt

    val ctx: CanvasRenderingContext2D = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    val input$ = Observable.fromEvent(document.body, "keypress")
      .map(_.asInstanceOf[KeyboardEvent])
      .map(_.charCode)

    val left$ = input$.filter(_ == 97).subscribe(s => println("left"))
    val right$ = input$.filter(_ == 100).subscribe(s => println("right"))
    val up$ = input$.filter(_ == 119).subscribe(s => println("up"))
    val down$ = input$.filter(_ == 115).subscribe(s => println("down"))

    Observable.fromEvent(document.body, "keypress")
      .map(_.asInstanceOf[KeyboardEvent])
      .map(_.charCode)
      .subscribe(
      s => println(s)
    )
  }
}
