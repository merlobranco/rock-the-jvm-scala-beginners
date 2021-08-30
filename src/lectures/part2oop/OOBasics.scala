package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  person.greet("Brais")
}

// Constructor
// Class parameters (from the constructor) are not class members (fields)
class Person(name: String, val age: Int = 0) {
  // In every instantiation the whole code bellow will be evaluated

  // body
  val x = 2

  println(1 + 3)

  def greet(name: String) = println(s"${this.name} says, Hi, $name")

  // Overloading
  def greet() = println(s"Hi, $name")

  // Multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
