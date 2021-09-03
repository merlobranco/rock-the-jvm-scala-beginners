package lectures.part2oop

object Generics extends App {

  // Traits can be also type parameterized
  //class MyList[A] {
  class MyList[+A] {
    // Use the type A
    // => WRONG with covariant approach
    // Covariant type A occurs in contravariant position in type A of value element
    // def add(element: A): MyList[A] = ???
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Dog that is Animal like a Cat
      If I add a Dog to my list, it will turn my list into a list of animals
    */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // Generic methods
  // Objects cannot be type parameterized
  // We are declaring generic methods inside the companion for the class MyList declared above
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // VARIANCE PROBLEM

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // The question is Does a List of Cats or Dogs extend from a List of Animals?

  // 1st answer: Yes List[Cat] extends List[Animal] = This behaviour is called COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) in theory is possible but since animal list is a list of cats, the list will be polluted
  // This will turn my list of cats into a list of animals (something more generic), since cats and dogs are extending from animals

  // 2nd answer: No List of cats and list of animals are two separated things = This behaviour is called INVARIANCE
  // Every type is in own world
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3rd answer: Hell, no! CONTRAVARIANCE
  // In this case I am provided with a general trainer of animals when I asking for a particular trainer of cats
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // Bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  // WRONG!!!
  // class Car
  // val newCage = new Cage(new Car) Will fail during compilation

  // EXERCISE
  // Expand MyList to be generic
}
