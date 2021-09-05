package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,2,3,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // Retrieve the value at this particular index
  val biggerSequence = aSequence ++ Seq(7,6,5)
  println(aSequence ++ Seq(7,6,5))
  println(biggerSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  val anotherRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // Lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  println(prepended)

  // Prepending operators :: +:, appending operator :+
  val anotherList = 42 +: aList :+ 89

  println(anotherList)

  // Filling a list with n elements of certain type
  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-")) // Pretty print

  // Arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  threeElements.foreach(println)
  // In Arrays for primitive types, their elements will have a default value like 0 for Int
  // In Arrays for reference types, like Strings, their elements will have a default value of null

  // Mutation. Arrays could be mutated in place
  numbers(2) = 0 // Syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // Arrays and Sequences
  val numbersSeq: Seq[Int] = numbers // Implicit conversion
  println(numbersSeq)

  // Vectors
  // Default implementation of Sequences
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // Vector vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = Random
    // Remember for-comprehension
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // Random value from 0 to maxCapacity
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 /maxRuns
  }

  val numbersList = {1 to maxCapacity}.toList
  val numbersVectors = {1 to maxCapacity}.toVector

  // Advantages: Keeps references to tail
  // Disadvantages: Updating an element in the middle takes long
  println(getWriteTime(numbersList))
  // Advantages: Depth of the tree is small
  // Disadvantages: Needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVectors))
}
