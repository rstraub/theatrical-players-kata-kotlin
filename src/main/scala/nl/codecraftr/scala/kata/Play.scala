package nl.codecraftr.scala.kata

final case class Play(name: String, `type`: String) {
  def calculateCosts(audience: Int): Int = {
    var costs = 0
    `type` match {
      case "tragedy" => {
        costs = 40000
        if (audience > 30)
          costs += 1000 * (audience - 30)
      }
      case "comedy" => {
        costs = 30000
        if (audience > 20)
          costs += 10000 + 500 * (audience - 20)
        costs += 300 * audience
      }
      case _ => throw new Exception("unknown type: " + `type`)
    }
    costs
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
