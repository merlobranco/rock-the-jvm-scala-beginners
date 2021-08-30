package lectures.part1basics

object CBNvsCBV extends App {

  // The expression once, from the very beginning, and then its value is provided to the function
  // the value is computed before is called, same value is used everywhere
  def callByValue(x: Long): Unit = {
    println("By value: " + x)
    println("By value: " + x)
  }

  // The expression is passed literally
  // The expression is evaluated every time is used within
  // Delays the evaluation of the passed expression until is used
  def callByName(x: => Long): Unit = {
    println("By name: " + x)
    println("By name: " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  // Equivalent to
  // callByValue(239708540516700L)
  // callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // printFirst(infinite(), 34) This will fail leading to a stack overflow
  printFirst(34, infinite()) // I won't fail because the evaluation of infinite is delayed until is executed, since is never used, it is never evaluated

}
