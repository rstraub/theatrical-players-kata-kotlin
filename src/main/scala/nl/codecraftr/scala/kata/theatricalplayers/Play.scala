package nl.codecraftr.scala.kata.theatricalplayers

// TODO this can become a hierarchy and polymorphic
case class Play(name: String, `type`: String) {
  def calculateCosts(audience: Int): Int = {
    `type` match {
      case "tragedy" =>
        var costs = 40000
        if (audience > 30)
          costs += 1000 * (audience - 30)
        costs
      case "comedy" =>
        var costs = 30000
        if (audience > 20)
          costs += 10000 + 500 * (audience - 20)
        costs += 300 * audience
        costs
      case _ => throw new Exception("unknown type: " + `type`)
    }
  }

  def calculateCredits(audience: Int): Int = {
    var playCredits = 0
    // Add credits for every attendee above 30
    playCredits += Math.max(audience - 30, 0)
    // add extra credit for every ten comedy attendees
    if ("comedy" == `type`)
      playCredits += Math.floor(audience / 5d).toInt
    playCredits
  }
}

sealed trait PlayX {
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

final case class Tragedy(name: String) extends PlayX

final case class Comedy(name: String) extends PlayX {
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
