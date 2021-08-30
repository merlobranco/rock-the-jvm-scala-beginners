package lectures.part2oop

object Objects {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  // Objects can be created like classes but they do not receive parameters
  // Objects can have var val and method definitions
  object Person {
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly = false

    // Factory method. Pattern that it's widely used in Scala
    def apply(mother: Person, father: Person, name: String) = new Person(name)
  }
  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS: Pattern of writing objects and classes at the same scope

  def main(args: Array[String]): Unit = {

    println(Person.N_EYES)
    println(Person.canFly)

    // To use class level definitions in Scala we use objects
    // Scala object = SINGLETON INSTANCE
    var person1 = Person
    var person2 = Person
    println(person1 == person2)

    val mary = new Person("Mary")
    val john = new Person("John")
    // Here obviously we have 2 different instances
    println(mary == john)

    val bobbie = Person(mary, john, "Bobbie")
  }

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
  // Equivalent to object MyApp extends App
}