package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val nodeOption: Option[Int] = None

  println(myFirstOption)

  // WORK with unsafe APIs
  def unsafeMethod(): String = null
//  val result = Some(null) // WRONG
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // Chained Methods
  def backupMethod(): String = "A valid result"
  val chainResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // Functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - USE GENERALLY NOT RECOMMENDED

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // For-comprehensions

  /*
    Given
   */
  val config: Map[String, String] = Map(
    // Fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // Connecting to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  /*
    Exercise
      1. Try to establish a connection, if so - print the connect method
   */
  println("Connecting...")
  val host = config.get("host")
  val port = config.get("port")
  /*
    // Imperative programming equivalent
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)

    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  /*
    // Imperative programming equivalent
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)
  /*
    // Imperative programming equivalent
    if (connectionStatus == null) println(None) else println(Some(connectionStatus.get))
   */
  println(connectionStatus)
  /*
    // Imperative programming equivalent
    if (status != null)
      println (status)
   */
  connectionStatus.foreach(println)

  /*
    ANOTHER OPTION
    CHAIN METHOD SOLUTION
  */

  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection(h, p))
        .map(c => c.connect))
    .foreach(println)

  // FOR-COMPREHENSIONS

  val connectionStatusFor = for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection (h, p)
  } yield
    c.connect
  connectionStatusFor.foreach(println)
}
