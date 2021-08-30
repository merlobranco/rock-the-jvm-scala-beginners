package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String) = movie == favoriteMovie

    //def hangOutWith(person: Person) = s"${this.name} is hanging out with ${name}"
    def +(person: Person) = s"${this.name} is hanging out with ${name}"

    def unary_! = s"$name, what the heck?!"

    def isAlive = true
    def apply() = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  // Infix notations = operator notation (syntactic sugar). Only works with methods with single parameter
  println(mary likes "Inception")

  // "OPERATORS" in Scala
  val tom = new Person("Tom", "Flight Club")
  //println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS
  // Akka actors have ! ?

  // PREFIX NOTATIONS (Another form of syntactic sugar)
  val x = -1
  val y = 1.unary_-
  // unary_ prefix only works with + - ~ !

  println(!mary)
  println(mary.unary_!)

  // POSTFIX NOTATIONS (Another form of syntactic sugar)
  // Only available for methods without parameters
  println(mary.isAlive)
  println(mary isAlive)

  // Apply
  println(mary.apply())
  println(mary()) // equivalent, it's looking for a definition of apply inside the class
}
