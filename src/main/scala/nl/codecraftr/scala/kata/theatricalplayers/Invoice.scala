package nl.codecraftr.scala.kata.theatricalplayers

final case class Invoice(customer: String, performances: List[Performance]) {
  def calculateCosts(): Int =
    performances
      .map(perf => perf.play.calculateCosts(perf.audience))
      .sum

  def calculateCredits(): Int =
    performances
      .map(perf => perf.play.calculateCredits(perf.audience))
      .sum
}
