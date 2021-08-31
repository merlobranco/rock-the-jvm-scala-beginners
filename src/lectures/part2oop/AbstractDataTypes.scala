package lectures.part2oop

object AbstractDataTypes extends App {

  // ABSTRACT
  abstract class Animal {
    val creatureType: String = "Wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    override def eat: Unit = println("crunch crunch")
  }

  // TRAITS
  // Unlike abstract classes the can be inherited along classes
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Reptile"

    override def eat: Unit = println("nom nom nom")

    override def eat(animal: Animal): Unit = println(s"I am a croc and I am eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc eat dog

  // TRAITS VS ABSTRACTS
  // Both could have abstract and non-abstract members
  // Differences:
  // 1 - Traits do no have constructor parameters
  // 2 - Multiple traits may be inherited by the same class
  // 3 - Traits = behaviour, abstract class = "thing". Example Animals describes animals but traits describes what they do
}
