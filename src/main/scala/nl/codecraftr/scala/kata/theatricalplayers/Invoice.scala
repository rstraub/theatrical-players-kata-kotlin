package nl.codecraftr.scala.kata.theatricalplayers

final case class Invoice(customer: String, performances: List[Performance]) {
  def calculateCosts(
      plays: Map[String, Play]
  ): Int = {
    var totalAmount = 0
    for (perf <- performances) {
      val play = plays(perf.playId)
      val performanceCost = play.calculateCosts(perf.audience)
      totalAmount += performanceCost
    }
    totalAmount
  }

  def calculateCredits(
      plays: Map[String, Play]
  ): Int = {
    var totalCredits = 0
    for (perf <- performances) {
      val play = plays(perf.playId)

      totalCredits += play.calculateCredits(perf.audience)
    }
    totalCredits
  }
}
