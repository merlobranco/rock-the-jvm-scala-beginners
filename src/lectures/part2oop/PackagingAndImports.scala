package lectures.part2oop

//import playground.{Cindirella, PrinceCharming}
import playground.{Cindirella => Princess, PrinceCharming} // Aliasing
// Same as import playground._

import java.util.Date
import java.sql.{Date => DateSql}

class PackagingAndImports extends App {

  // Package members are accessible by their simple name
  val writer = new Writer("Daniel", "", 2018)

  // Import the package
  val princess = new Princess // We could use also the the fully qualified class name instead the import

  // Packages are in hierarchy
  // Matching folder structure

  // Package object
  // Can only be one per package
  // What about with want universal constant and universal methods outside any class

  sayHello
  println(SPEED_OF_LIGHT)

  // Imports

  val prince = new PrinceCharming

  val date = new Date
  val dateSql = new DateSql(2018, 5, 4)

  // Default imports
  // Packages that are automatically imported without any import intention from our side
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
