package exercises

abstract class Maybe[+T] {
  def map[B](t: T => B): Maybe[B]
  def flatMap[B](tr: T => Maybe[B]): Maybe[B]
  def filter(p: T => Boolean): Maybe[T]
}

case object MaybeNot extends Maybe[Nothing] {
  override def map[B](t: Nothing => B): Maybe[B] = MaybeNot
  override def flatMap[B](tr: Nothing => Maybe[B]): Maybe[B] = MaybeNot
  override def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}

case class Just[+T](value: T) extends Maybe[T] {
  override def map[B](t: T => B): Maybe[B] = Just(t(value))

  override def flatMap[B](t: T => Maybe[B]): Maybe[B] = t(value)

  override def filter(p: T => Boolean): Maybe[T] =
    if (p(value)) this
    else MaybeNot
}

object MaybeTest extends App {
  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))

}
