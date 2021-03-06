package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length) // Remember a function without parameters could be called without the last () 'parenthesis'

  val aNumberString = "45"
  val aNumber = aNumberString.toInt

  // Appending and prepending operators +: :+
  println('a' +: aNumberString :+ 'z')

  println(str.reverse)
  println(str.take(2))

  // SCALA-SPECIFICS: String interpolators

  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"
  println(greeting)
  println(anotherGreeting)

  // F-interpolators (format interpolators)
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  // Can check for type correctness
  //val x = 1.1f
  //val anotherStr = f"$x%3d" // Format requires an Int -> Compile error!

  // raw-interpolator
  println(raw"This is a \n newLine")
  val escaped = "This is a \n newLine"
  println(raw"$escaped") // The \n will be escaped
  val date = "2021-09-31"
  println("[" + date.substring(0, date.indexOf("-", 5)) + "]")
}
