package lectures.part4pm

import exercises.{Cons, MyList, Empty}

object AllThePatterns extends App {
/*

  // 1 - Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - Match anything
  // 2.1 Wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 Variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - Tuples
  val aTuple = (1,2)
  val matchTuple = aTuple match {
    case (1, 1) =>
    case (something , 2) => s"I've found $something" // We could have a nested matching
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedtuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  // PMs can be NESTED!!!

  // 4 - Case classes - Constructor pattern

  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
    case Cons(head, tail) =>
  }

  // 5 - List patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // Extractor for a list -> Advanced
    case List(1, _*) => // List of arbitrary length -> Advanced
    case 1 :: List(_) => // Infix pattern
    case List(1,2,3) :+ 42 => // Infix pattern
  }

  // 6 - Type specifiers
  val unknown: Any = 2
  val unknownMatching = unknown match {
    case list: List[Int] => // Explicit type specifier
    case _ =>
  }

  // 7 - Name binding (Explicit name binding)
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => // name binding => use the name 'nonEmptyList' later (here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
  }

  // 8 - Multi-patterns (multiple patterns chained y a typed operator)
  val multipattern = aList match {
    case Empty | Cons(0, _) => // Compound pattern (multi-pattern)
  }

  // 9 - If guards (For filtering matched elements)

  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }
*/

  /*
    Exercise
   */

  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)

  /*
    Surprisingly this return a list of Strings
    Because the backwards compatibility of JVM
    JVM 5 erases all the generic types, since JVM 1 does no have

    So finally we will have:
    Because our type parameters are erased

      val numbersMatch = numbers match {
        case listOfStrings: List => "a list of strings"
        case listOfNumbers: List => "a list of numbers"
        case _ => ""
      }

  */

}
