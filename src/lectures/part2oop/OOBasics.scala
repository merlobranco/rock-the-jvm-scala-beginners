package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  person.greet("Brais")

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(imposter))
  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
  counter.decrement(10).inc(5).print
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
/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
  - authorAge 'at the year of release'
  - isWrittenBy(author)
  - copy(new year of release) = new instance of Novel
 */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName = s"$firstName $surname"
}

class Novel(name: String, releaseYear: Int, author: Writer) {
  def authorAge = releaseYear - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newReleaseYear: Int) = new Novel(name, newReleaseYear, author)
}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload increment/decrement to receive an amount => new Counter
 */

class Counter(val count: Int = 0) {
  // Immutability, Instances are fixed, cannot be modified inside,
  // whenever we need to modify the content of an instance we actually need to return a new instance
  def increment(amount: Int = 1) = new Counter(count + amount)
  def decrement(amount: Int = 1) = new Counter(count - amount)

  def inc: Counter = {
    println("Incrementing")
    new Counter(count + 1)
  }

  def dec: Counter = {
    println("Decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}

