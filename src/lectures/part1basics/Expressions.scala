package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)
  // + - * / & ^ << >> >>> (right shift with zero extensions)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ..... side effects (are expressions in Scala returning units)

  // Instructions (DO) vs Expressions (VALUE)

  // IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)

  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // LOOPS are not recommended at all in Scala

  // EVERYTHING in Scala is an Expression

  val aWeirdValue = (aVariable = 3) // Unit === void in other languages
  println(aWeirdValue)

  // Side effects: println(), whiles, reassigning (All of them are expressions returning units)

  // Code blocks: expressions with special properties
  // the value of the code block is the value of the last expression included on it

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // EXPRESSIONS vS. INSTRUCTIONS
  // * instructions are executed (think Java), expressions are evaluated (Scala)
  // * In Scala we'll think in terms of expressions

  // EXERCISES
  // 1. Difference between "hello world" vs println("hello world")?
  // Answer: first one is a value of type string, second one is an side effect expression returning an unit

  // 2. What is the value of the following code block

  val someValue = {
    2 < 3
  }

  // Answer: True

  // 3. What is the value of the following code block

  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }

  // Answer: 42

}
