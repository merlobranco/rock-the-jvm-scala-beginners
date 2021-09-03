package lectures.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // Map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // Filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1)

  println(list.flatMap(toPair))

  // EXERCISES
  // Print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")
  // List("a1", "a2", ..., "d4")

  // ITERATIONS
  val combinations = chars.flatMap(x => numbers.map(n => x + n.toString))
  val biggerCombinations = chars.flatMap(x => numbers.filter(n => n % 2 == 0).flatMap(n => colors.map(c => x + n.toString + "-" + c)))
  println(combinations)
  println(biggerCombinations)

  // FOREACH
  list.foreach(println)

  // FOR-COMPREHENSIONS
  val forCombinations = for {
    n <- numbers if n % 2 == 0 // Guards (Filtering)
    c <- chars
    color <- colors
  } yield  c + n.toString + "-" + color

  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // SYNTAX OVERLOAD
  list.map {
    x => x * 2
  }

  /*
    1. MyList supports for comprehensions? Yes
      map(f: A => B) => MyList[B]
      filter(p: A => Boolean) => MyList[A]
      flatMap(f: A => MyList[B]) => MyList[B]
    2. A small collection of at most ONE element - Maybe[+T]
      - map, flatMap, filter
   */
}
