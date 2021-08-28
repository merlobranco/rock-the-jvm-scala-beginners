package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int =
    if (n <= 0) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computing factorial of " + n)
      result
    }

  println("factorial(10) = " + factorial(10))
//  println("factorial(5000) = " + factorial(5000))

  def anotherFactorial(n: Int): BigInt = {
    // In order to figure out if our function is tail recursive we should use @tailrec
    // if the function is not tail recursive the compiler will throw an error
    @tailrec
    def factHelper(x: BigInt, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)
      // TAIL RECURSION = use recursive call as the LAST expression
      // The fact that we are writing the factHelper as the last expression of its code path
      // This allows Scala to preserve the same stack frame and not use additional stack frames for recursive calls
      //
      // In the previous implementation, Scala needed a recursive call stack for each recursive call
      // so that it computes the intermediate result so that it can then multiply it with a number
      // and the pass it back from the stack
    }

    factHelper(n, 1)
  }

  println("factorial(10) = " + anotherFactorial(10))

  /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 1 * 10)
    = factHelper(8, 1 * 9 * 10)
    = factHelper(7, 1 * 8 * 9 * 10)
    = ...
    = factHelper(2, 1 * 3 * .. * 9 * 10)
    = factHelper(1, 1 * 2 * .. * 9 * 10)
    = 1 * 2 * .. * 9 * 10
  */

  println("factorial(5000) = " + anotherFactorial(5000)) // With Int the result will be overflows the Int representation, let's use BigInt

  // WHEN WE NEED LOOPS, USE _TAIL_RECURSION

  /*
    1. Concatenate a string n times
    2. Is Prime function tail  recursive
    3. Fibonacci function, tail recursive.
    NOTE. The trick is to use these  little accumulators to store intermediate results rather than calling the function recursively
    we need as many accumulators as recursive calls we have on our code path
   */

  def concatenate(s: String, n: Int): String = {
    @tailrec
    def concatenateTailRec(s: String, n: Int, accumulator: String): String = {
      if (n <= 0) accumulator
      else concatenateTailRec(s, n-1, s + accumulator)
    }
    concatenateTailRec(s, n, "")
  }

  println("concatenate('hello', 5) = " + concatenate("hello", 5))

  def isPrime(n : Int) : Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRec(t - 1, n % t != 0  && isStillPrime)
    }

    isPrimeTailRec(n / 2, true)
  }

  println("isPrime(13) = " + isPrime(13))
  println("isPrime(256) = " + isPrime(256))

//  def fibonacci(n: Int): Int =
//    if (n <= 2) 1
//    else fibonacci(n - 1) + fibonacci(n - 2)

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailRec(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fibonacciTailRec(i + 1, last + nextToLast, last)
    }
    if (n <= 2) 1
    else fibonacciTailRec(2, 1, 1)
  }

  println("fibonacci(7) = " + fibonacci(7))
  println("fibonacci(8) = " + fibonacci(8))
  println("fibonacci(9) = " + fibonacci(9))
}
