package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }



  // Here we have an anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahaha")
  }
  /*
    Equivalent with:

      class AnonymousClasses$$anon$1 extends Animal {
        override def eat: Unit = println("hahahahaha")
      }
      val funnyAnimal: Animal = new AnonymousClasses$$anon$1

   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println("Hi, my name is Jim, how can I be of service?")
  }

  // Anonymous classes are working for Abstract and non Abstract classes as well
}
