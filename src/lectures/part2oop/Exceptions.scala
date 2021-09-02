package lectures.part2oop

import java.lang.NullPointerException

object Exceptions extends App {

  // 1. Throwing Exceptions
  val x: String = null
  // println(x.length)

  // val aWeirdValue: String = throw new NullPointerException // It does not hold a value but could be assign to things

  // Throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. How to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // Code that might throw
    getInt(true)
  } catch {
    //case e: RuntimeException => println("Caught a Runtime Exception")
    case e: RuntimeException => 43
  } finally {
    // Code that will get executed no matter what
    // Optional
    // Does not influence the return type of this expression
    // Use finally only for side effects. For example logging something into a file
    println("Finally!")
  }

  println(potentialFail)

  // 3. How to define your own Exceptions
  class MyException extends Exception
  val exception = new MyException

  // throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with StackOverflowError
    3. PocketCalculator
        - add(x, y)
        - subtract(x, y)
        - multiply(x, y)
        - divide (x, y)

        Throw
          - OverflowException if add(x, y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
          - MathCalculationException for division by 0
  */
  // OOM
  // val array = Array.ofDim(Int.MaxValue)

  // SO
  // def infinite: Int = 1 + infinite
  // val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide (x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      val result = x / y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

//    println(PocketCalculator.add(Int.MaxValue, 3))
//    println(PocketCalculator.divide(5, 0))
  }
}
