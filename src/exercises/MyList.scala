package exercises

import scala.annotation.tailrec

abstract class MyList {

  /*
    head = first element of list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new List with this element added
    toString => a string representation of the list
   */

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(e: Int): MyList

  // Polymorph
  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList {
  override def head: Int = throw new NoSuchElementException

  override def tail: MyList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(e: Int): MyList = new Cons(e, Empty)

  //override def printElements: String = ""

  override def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head: Int = h

  override def tail: MyList = t

  override def isEmpty: Boolean = false

  override def add(e: Int): MyList = new Cons(e, this)

//  override def printElements: String =
//    if (t.isEmpty) h.toString
//    else h + " " + t.printElements

  override def printElements: String = {
    @tailrec
    def printElementsTailRec(l: MyList, content: String = ""): String =
      if (l.tail.isEmpty) (content + " " + l.head).trim
      else printElementsTailRec(l.tail, content + " " + l.head)
    printElementsTailRec(this)
  }
}

object ListTest extends App {

  val list = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  println(list.tail.head)
  println(list.add(5).head)
  println(list.isEmpty)

  println(list.toString)

}
