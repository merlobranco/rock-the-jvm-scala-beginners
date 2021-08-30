package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFact(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    trFact(n - 1, acc)
  }

  val fact10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080) = println("Saving picture")

  savePicture(width = 800)

  /*
    In order to help the compiler which parameter is matching which argument
    1. Pass in every leading argument
    2. name the arguments
   */

  // Thanks to the names the parameters could be provided in different order
  savePicture(height = 600, width = 800, format = "bmp")
}
