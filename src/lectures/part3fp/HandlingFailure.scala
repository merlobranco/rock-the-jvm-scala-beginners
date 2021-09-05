package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // Syntax sugar
  val anotherPotentialFailure = Try {
    // Code that might throw an error
  }

  // Utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // IF We design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  // Map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  /*
    Given
   */
  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (/*random.nextBoolean()*/true) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (/*random.nextBoolean()*/true) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getConnectionSafe(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  /*
    Exercise
    If we get the html page from the connection, print it to the console i.e. call renderHTML
   */

  val possibleConnection = HttpService.getConnectionSafe(host, port)
  val possibleHtml = possibleConnection.flatMap(c => c.getSafe("/home"))
  possibleHtml.foreach(renderHTML)

  // Short hand version, chained methods

  HttpService.getConnectionSafe(host, port)
    .flatMap(c => c.getSafe("/home"))
    .foreach(renderHTML)

  // FOR-COMPREHENSIONS
  for {
    c <- HttpService.getConnectionSafe(host, port)
    p <- c.getSafe("/home")
  } yield renderHTML(p)

}
