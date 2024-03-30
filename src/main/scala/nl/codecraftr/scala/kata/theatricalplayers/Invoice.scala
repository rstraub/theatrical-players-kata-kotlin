package nl.codecraftr.scala.kata.theatricalplayers

final case class Invoice(customer: String, performances: List[Performance]) {
  lazy val totalCosts: Int =
    performances
      .map(perf => perf.play.calculateCosts(perf.audience))
      .sum

  lazy val totalCredits: Int =
    performances
      .map(perf => perf.play.calculateCredits(perf.audience))
      .sum
}
