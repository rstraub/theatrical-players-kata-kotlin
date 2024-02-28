package nl.codecraftr.scala.kata.theatricalplayers

final case class Invoice(customer: String, performances: List[Performance]) {
  def calculateCosts(
      plays: Map[String, Play]
  ): Int =
    performances
      .map(perf => plays(perf.playId).calculateCosts(perf.audience))
      .sum

  def calculateCredits(
      plays: Map[String, Play]
  ): Int =
    performances
      .map(perf => plays(perf.playId).calculateCredits(perf.audience))
      .sum
}
