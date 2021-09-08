package lectures.part4pm

object PatternsEverywhere extends App {

  // Big idea #1
  try {
    // Code
  } catch {
    case e: RuntimeException => "Runtime"
    case npe: NullPointerException => "npe"
    case _ => "Something else"
  }

  // Catches are actually matches
  /*
  try {
    // Code
  } catch (e) {
        e match {
          case e: RuntimeException => "Runtime"
          case npe: NullPointerException => "npe"
        case _ => "Something else"
        }
  }
*/

  // Big idea #2
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // Does it sound familiar?
  } yield 10 * x

  println(evenOnes)

  // Generators are also based on PATTERN MATCHING
  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // Case classes, :: operators, ...

  // Big idea #3
  val tuple = (1,2,3)
  val (a,b,c) = tuple
  println(a)
  println(b)
  println(c)
  // Multiple value definition based on PATTERN MATCHING
  // ALL THE POWER

  val head :: tail = list
  println(head)
  println(tail)

  // Big idea #4
  // Partial function are also based on PATTERN MATCHING
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "Something else"
  } // Partial function literal

  val mappedList2 = list.map { x => x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "Something else"
    }
  }
  println(mappedList)


}
