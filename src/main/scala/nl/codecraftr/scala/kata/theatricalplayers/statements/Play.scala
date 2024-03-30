package nl.codecraftr.scala.kata.theatricalplayers.statements

sealed trait Play {
  def name: String

  def calculateCosts(audience: Int): Int = {
    val baseCosts = 40000
    if (audience > 30) baseCosts + 1000 * (audience - 30)
    else baseCosts
  }

  def calculateCredits(audience: Int): Int = Math.max(audience - 30, 0)
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
      // add extra credit for every ten comedy attendees
      val bonusComedyCredits = Math.floor(audience / 5d).toInt
      super.calculateCredits(audience) + bonusComedyCredits
  }
}
