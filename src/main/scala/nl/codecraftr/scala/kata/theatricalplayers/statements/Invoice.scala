package nl.codecraftr.scala.kata.theatricalplayers.statements

final case class Invoice(customer: String, performances: List[Performance]) {
  lazy val totalCosts: Int =
    performances
      .map(_.totalCosts)
      .sum

  lazy val totalCredits: Int =
    performances
      .map(_.totalCredits)
      .sum
}
