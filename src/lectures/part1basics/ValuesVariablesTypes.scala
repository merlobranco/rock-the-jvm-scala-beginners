package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x = 42
  println(x)

  // Vals are immutable

  // Compiler can infer types

  // No need for ; at the end of the line
  val aString: String = "hello"

  val aBoolean: Boolean = true
  val aChart: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 2343423423L
  val aFloat: Float = 2.0F
  val aDouble: Double = 3.14

  // VARIABLES //

  var aVariable: Int = 4
  aVariable = 5 // side effects


}
