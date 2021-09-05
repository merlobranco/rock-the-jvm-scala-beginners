package lectures.part3fp

import scala.annotation.tailrec
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

  /*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900?
      a) Overrides the first pair key, value jim -> 500 by jim -> 900
      b) Careful with Mapping keys!!!

    2. Overly simplified social network based on maps
      Person String
      - add a person to the network
      - remove
      - friend (mutual)
      - unfriend

      - num of friends of a person
      - person with most friends
      - how many people have NO friends
      - if there is a social connection between two people direct or not
   */

  val mapTest = Map(("Jim", 555),("JIM", 900)).map(pair => pair._1.toLowerCase -> pair._2)
  println(mapTest)

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    if (network.contains(person)) network
    else network + (person -> Set())


  def friend(network: Map[String, Set[String]], pair: (String, String)): Map[String, Set[String]] =
    network + (pair._1 -> (network(pair._1) + pair._2)) + (pair._2 -> (network(pair._2) + pair._1))

  def unfriend(network: Map[String, Set[String]], pair: (String, String)): Map[String, Set[String]] = {
    network + (pair._1 -> (network(pair._1) - pair._2)) + (pair._2 -> (network(pair._2) - pair._1))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @tailrec
    def removeTailRec(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeTailRec(friends.tail, unfriend(networkAcc, (person,friends.head)))

    if (!network.contains(person)) network
    else removeTailRec(network(person), network) - person
  }

  def numFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  def personMostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  def numPeopleNoFriends(network: Map[String, Set[String]]): Int =
//    network.view.filterKeys(k => network(k).isEmpty).toMap.size
//    network.count(pair => pair._2.isEmpty)
    network.count(_._2.isEmpty)

  def connection(network: Map[String, Set[String]], pair: (String, String)): Boolean = {
    // Breadth first search
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], yetToDiscoverPeople: Set[String]): Boolean = {
      if (yetToDiscoverPeople.isEmpty) false
      else {
        val person = yetToDiscoverPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, yetToDiscoverPeople.tail)
        else bfs(target, consideredPeople + person, yetToDiscoverPeople.tail ++ network(person))
      }
    }
    bfs(pair._2, Set(), network(pair._1) + pair._1) // It's possible I call this method with (pair._1, pair._1)
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")

  println(network)
  println(friend(network, ("Bob", "Mary")))
  println(unfriend(friend(network, ("Bob", "Mary")), ("Mary", "Bob")))
  println(remove(friend(network, ("Bob", "Mary")), "Bob"))

  // Jim, Bob, Mary
  val people = add(add(add(Map(),"Jim"),"Bob"),"Mary")
  val jimBob = friend(people,("Jim", "Bob"))
  val testNet = friend(jimBob,("Mary", "Bob"))

  println(testNet)

  println(numFriends(testNet, "Bob"))

  println(personMostFriends(testNet))
  println(numPeopleNoFriends(testNet))

  println(connection(testNet, ("Mary","Jim")))
  println(connection(network, ("Bob","Mary")))
}
