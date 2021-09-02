package lectures.part3fp

object AnonymousFunctions extends App {

  // Anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2
//  val doubler: Int => Int = x => x * 2

  // Same as
//  val doubler = new Function1[Int, Int] {
//    override def apply(x: Int): Int = x * 2
//  }

  // Multiple params
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a * b

  // No params
  val alwaysTrue: () => Boolean = () => true

  println(alwaysTrue) // Function itself
  println(alwaysTrue()) // Actual call

  // Curly braces with lambdas
  val stringToInt = {
    (str: String) => str.toInt
  }

  // MORE syntactic sugar
  // The types always should be provided in order to solve the _
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
    EXERCISES

    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder as an anonymous function
   */

  val returningFunction = (a: Int) => (b: Int) => a + b

  println(returningFunction(45)(43))
}
