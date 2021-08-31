package lectures.part2oop

object Inheritance extends App {
  class Animal {
    val creatureType = "wild"
    /*protected*/ def eat = println("nom nom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // CONSTRUCTORS
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // OVERRIDING
  class Dog(override val creatureType: String) extends Animal {
    //override val creatureType = "domestic"
    override def eat = {
      println("crunch crunch")
      super.eat
    }
  }

  //val dog = new Dog
  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // Type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  // A method call will go to the most override method whenever is possible
  unknownAnimal.eat

  // OVERRIDING AND OVERLOADING

  // SUPER

  // PREVENTING OVERRIDE
  // 1) Use key word final on member
  // 2) Use key word final on the class, preventing the whole class for being extended
  // 3) Use key word sealed. Seal the class = extend classes in this file, but prevent extension in other files

}
