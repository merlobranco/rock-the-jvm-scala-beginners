package lectures.part3fp

object WhatsAFunction extends App {

  // Goal: Use functions as first class elements
  // Problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // Function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  // By default Scala supports these Function traits up to 22 parameters

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS, OR INSTANCES OF CLASSES DERIVING FROM FUNCTION1, ..., FUNCTION22

  /*
    1. A function takes 2 string and concatenates them
    2. Transform the MyPredicate and MyTransformer into function types
    3. Define a function which takes an int and returns another function which takes an int and returns another int
      - what's the type of this function
      - how to do it
   */

  val concat = new ((String, String) => String) {
    override def apply(a: String, b: String): String = a + b
  }

  /*
    Same as

    val concat = new Function2[String, String, String] {
      override def apply(a: String, b: String): String = a + b
    }
  */

  println(concat("Going for ", "lunch"))

  // Higher-order functions, functions that receive other functions as parameters or return other functions
  val returningFunction = new (Int => Int => Int) {
    override def apply(a: Int): Int => Int = (b: Int) => a + b
  }

 /*
   Same as

   val returningFunction = new Function1[Int, Function1[Int, Int]] {
     override def apply(a: Int): Function1[Int, Int] = new Function1[Int, Int] {
       override def apply(b: Int): Int = a + b
     }
   }
  */

  val f = returningFunction(1);
  println(f(2))
  println(returningFunction(5)(6)) // Curried function
}

//class Action {
//  def execute(element: Int): String = ???
//}

//trait Action[A, B] {
//  def execute(element: A): B
//}

trait MyFunction[A, B] {
  def apply(element: A): B
}
