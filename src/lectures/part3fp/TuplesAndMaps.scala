package lectures.part3fp

import scala.runtime.IntRef

object TuplesAndMaps extends App {

  // Tuples = finite ordered "lists"
  val aTuple = (2, "Hello, Scala") // Tuple2[Int, String] = Int (Int, String)

  // Tuples can group at most 22 elements of different types
  // 22 because they are used in conjunction with Function types, we have also available 22 Function types

  println(aTuple._1) // 2
  println(aTuple._2) // "Hello, Scala"

  println(aTuple.copy(_2 = "Goodbye, Java"))
  println(aTuple.swap) // ("Hello, Scala", 2)

  // Maps - Keys -> Values (Index -> Data)
  val aMap: Map[String, Int] = Map()

  //val phoneBook = Map("Jim" -> 555,"Daniel" -> 789)
  val phoneBook = Map(("Jim", 555),("Daniel", 789)).withDefaultValue(-1) // For not throwing a NoSuchElementException

  println(phoneBook)

  // Basic map operations
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))

  // Add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing

  // We will create a new phonebook because maps are immutables
  println(newPhoneBook)

  // Functional on maps
  // Map, flatmap, filter

  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)

  // mapValues
  //println(phoneBook.view.mapValues(number => number * 10).toMap)
  println(phoneBook.view.mapValues(number => "0245-" + number).toMap)

  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)

  // Group by
  val names = List("Bob","James","Angela","Mary","Daniel","Jim")
  println(names.groupBy(name => name.charAt(0)))

}
