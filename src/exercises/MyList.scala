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

  // Polymorph
  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](e: B): MyList[B] = new Cons(e, Empty)

  override def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](e: B): MyList[B] = new Cons(e, this)

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

object ListTest extends App {

  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  println(listOfIntegers.tail.head)
  println(listOfIntegers.add(5).head)
  println(listOfIntegers.isEmpty)

  println(listOfIntegers.toString)

  val listOfStrings = new Cons("Hello", new Cons("How", new Cons("are", new Cons("you", Empty))))
  println(listOfStrings.tail.head)
  println(listOfStrings.add("?").head)
  println(listOfStrings.isEmpty)

  println(listOfStrings.toString)

}
