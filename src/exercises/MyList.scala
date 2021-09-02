package exercises

import scala.annotation.tailrec

abstract class MyList[+A] {

  /*
    head = first element of list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new List with this element added
    toString => a string representation of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](e: B): MyList[B]

  def map[B](t: A => B): MyList[B]
  def filter(p: A => Boolean): MyList[A]
  def flatMap[B](tr: A => MyList[B]): MyList[B]

  def +[B >: A](l: MyList[B]): MyList[B]

  // Polymorph
  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](e: B): MyList[B] = new Cons(e, Empty)

  override def printElements: String = ""

  // Higher-order functions, functions that receive other functions as parameters or return other functions
  override def map[B](tr: Nothing => B): MyList[B] = Empty

  override def filter(p: Nothing => Boolean): MyList[Nothing] = Empty

  override def flatMap[B](tr: Nothing => MyList[B]): MyList[B] = Empty

  override def +[B >: Nothing](l: MyList[B]): MyList[B] = l
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](e: B): MyList[B] = Cons(e, this)

  override def map[B](tr: A => B): MyList[B] =
   Cons(tr(h), t.map(tr))

  override def filter(p: A => Boolean): MyList[A] =
    if (p(h)) Cons(h, t.filter(p))
    else t.filter(p)

  /*
    [1,2].flatMap(n=> [n, n+1])
    = [1, 2] + [2].flatMap(n=> [n, n+1])
    = [1, 2] + [2, 3] + Empty.flatMap(n=> [n, n+1])
    = [1, 2] + [2, 3] + Empty
    = [1, 2] + [2, 3]
    = [1, 2, 2, 3]
   */
  override def flatMap[B](tr: A => MyList[B]): MyList[B] =
    tr(h) + t.flatMap(tr)

  /*
    [1, 2] + [3, 4, 5]
    = new Cons(1, [2] + [3, 4, 5])
    = new Cons(1, new Cons(2, Empty + [3, 4, 5]))
    = new Cons(1, new Cons(2, [3, 4, 5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons (4, new Cons(5, Empty)))))
   */
  override def +[B >: A](l: MyList[B]): MyList[B] = new Cons(h, t + l)

//  override def printElements: String =
//    if (t.isEmpty) h.toString
//    else h + " " + t.printElements

  override def printElements: String = {
    @tailrec
    def printElementsTailRec(l: MyList[A], content: String = ""): String =
      if (l.tail.isEmpty) (content + " " + l.head).trim
      else printElementsTailRec(l.tail, content + " " + l.head)
    printElementsTailRec(this)
  }

}

//trait MyPredicate[-T] {
//  def test(e: T): Boolean
//}

// If we don't declare A like a contravariant, my code that using the MyTransformer type won't compile
// because on the ded map the cryptic covariance type occurs in an invariant position
//trait MyTransformer[-A, B] {
//  def transform(e: A): B
//}

object ListTest extends App {

  val listOfIntegers = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  val cloneListOfIntegers = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  println(listOfIntegers.tail.head)
  println(listOfIntegers.add(5).head)
  println(listOfIntegers.isEmpty)

  println(listOfIntegers.toString)

  val listOfStrings = Cons("Hello", Cons("How", Cons("are", Cons("you", Empty))))
  println(listOfStrings.tail.head)
  println(listOfStrings.add("?").head)
  println(listOfStrings.isEmpty)

  println(listOfStrings.toString)
//  println(listOfIntegers.filter((e: Int) => e % 2 == 0))
  println(listOfIntegers.filter(_ % 2 == 0))
  /*
    Same as

    println(listOfIntegers.filter(new MyPredicate[Int] {
      override def test(e: Int): Boolean = e % 2 == 0
    }))
   */

//  println(listOfStrings.filter((e: String) => e.length > 3))
  println(listOfStrings.filter(_.length > 3))

//  println(listOfIntegers.map((e: Int) => e * 3))
  println(listOfIntegers.map(_ * 3))
  /*
    Same as

    println(listOfIntegers.map(new MyTransformer[Int, Int] {
      override def transform(e: Int): Int = e * 3
    }))
   */

//  println(listOfStrings.map((e: String) => e.toUpperCase()))
  println(listOfStrings.map(_.toUpperCase()))

  val listOfIntegers2 = Cons(10, Cons(20, Cons(30, Cons(40, Empty))))

  println(listOfIntegers + listOfIntegers2)

  // println(listOfIntegers.flatMap((e: Int) => new Cons(e, new Cons(e + 1, Empty))))

  /*
    Same as

    println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
      override def apply(e: Int): MyList[Int] = new Cons(e, new Cons(e + 1, Empty))
    }))
  */
  println(listOfIntegers.flatMap((e: Int) => Cons(e, Cons(e + 1, Empty))))

  // By converting Empty and Cons to case objects and classed we just made them serializable and we are able to use them through the network
  println(cloneListOfIntegers == listOfIntegers)
}
