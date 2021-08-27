package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("a", 3))

  // WHEN WE NEED LOOPS, USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  // We could define auxiliary functions inside bigger functions
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n-1)
  }

  // EXERCISES

  /*
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old"
    2. A factorial function  1 * 2 * 3 * .. * 4
    3. A Fibonacci function
      f(1) = 1
      f(2) = 2
      f(n) = f(n - 1) + f(n - 2)
    4. Tests if a number is prime
   */

  def greeting(name: String, age: Int) =
    "Hi, my name is " + name + " and I am " + age + " years old"

  println(greeting("Samira", 35))

  def factorial(n: Int): Int =
    if (n <= 0) 1
    else n * factorial(n - 1)

  println("factorial(7) = " + factorial(7))

  def fibonacci(n: Int): Int =
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)

  println("fibonacci(7) = " + fibonacci(7))

  var i = 2
  def isPrime(n: Int): Boolean =
    if (n <= 1) false
    else if (n  == i) true
    else if (n % i == 0) false
    else {
      i = i + 1
      isPrime(n)
    }

  println("isPrime(131) = " + isPrime(131))

  def isPrimeOther(n : Int) : Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  println("isPrime(131) = " + isPrimeOther(131))

}
