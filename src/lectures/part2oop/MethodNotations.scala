package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, age: Int = 0) {
    def likes(movie: String) = movie == favoriteMovie

    //def hangOutWith(person: Person) = s"${this.name} is hanging out with ${name}"
    def +(person: Person) = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String) = new Person( s"${name} (${nickname})", favoriteMovie)

    def learns(subject: String) = s"${name} learns ${subject}"
    // Equivalent def learns: String = this learns "Scala"
    // Equivalent def learns: String = learns("Scala")

    def unary_! = s"$name, what the heck?!"
    def unary_+ = new Person(name, favoriteMovie, age + 1)

    def isAlive = true
    def is = s"${name} is ${age} years old"
    def apply() = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(n: Int) = s"$name watched $favoriteMovie $n times"
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

  /*
    1.  Overload the + operator
        mary + "the rockstar" => new person "Mary (the rockstar)"

    2.  Add age to the Person class
        Add an unary + operator => new person with the age + 1
        +mary => mary with the age incremented

    3.  Add a "learns" method in the Person class => "Mary learns Scala"
        Add a learnScala method, calls learns method with "Scala".
        Use it in postfix notation.

    4.  Overload the apply method
        mary.apply(2) => "Mary watched Inception 2 times"
   */

  println((mary + "the rockstar")())

  println(mary is)
  println(+mary is)

  println(mary learns "Python")
  println(mary learns)
  println(mary(2))

}
