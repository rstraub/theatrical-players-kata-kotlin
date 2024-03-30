package nl.codecraftr.scala.kata.theatricalplayers

sealed trait Play {
  def name: String

  def calculateCosts(audience: Int): Int = {
    var costs = 40000
    if (audience > 30)
      costs += 1000 * (audience - 30)
    costs
  }

  def calculateCredits(audience: Int): Int = {
    var playCredits = 0
    playCredits += Math.max(audience - 30, 0)
    playCredits
  }
}

final case class Tragedy(name: String) extends Play
final case class History(name: String) extends Play
final case class Pastoral(name: String) extends Play

final case class Comedy(name: String) extends Play {
  override def calculateCosts(audience: Int): Int = {
    var costs = 30000
    if (audience > 20)
      costs += 10000 + 500 * (audience - 20)
    costs += 300 * audience
    costs
  }

  override def calculateCredits(audience: Int): Int = {
    var playCredits = 0
    // Add credits for every attendee above 30
    playCredits += Math.max(audience - 30, 0)
    // add extra credit for every ten comedy attendees
    playCredits += Math.floor(audience / 5d).toInt
    playCredits
  }
}
