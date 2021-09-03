package lectures.part3fp

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Higher order function (HOF)

  // map, flapMap, filter in MyList

  // Function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n - 1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))


  println(nTimes(a => a * 2, 3, 4))

  // ntb(f, n) => f(f(f...(x)))
  // Considering f = x => x + 1
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne...(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))
  }

  println(nTimesBetter(a => a * 2, 3)(4))

  // Curried functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // Functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String  = c.format(x)

  // Always we should specify the types for the smaller functions
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  // Later we could create sub functions and applying whatever value I want later

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
