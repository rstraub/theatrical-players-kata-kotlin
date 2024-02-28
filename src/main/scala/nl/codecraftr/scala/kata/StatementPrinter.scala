package nl.codecraftr.scala.kata

import java.lang.System.lineSeparator
import java.text.NumberFormat
import java.util.Locale

final case class Invoice(customer: String, performances: List[Performance])
final case class Performance(playId: String, audience: Int)
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

class StatementPrinter {
  private val culture = Locale.US

  def print(invoice: Invoice, plays: Map[String, Play]): String = {
    val totalCosts: Int = calculateTotalCosts(invoice, plays)
    val totalCredits = calculateTotalCredits(invoice, plays)

    var result = createHeader(invoice)
    result += createLines(invoice, plays)
    result += createFooter(totalCosts, totalCredits)

    result
  }

  private def createHeader(invoice: Invoice) =
    s"Statement for ${invoice.customer}$lineSeparator"

  private def createLines(invoice: Invoice, plays: Map[String, Play]) = {
    var lines = ""
    for (perf <- invoice.performances) {
      val play = plays(perf.playId)
      val performanceCost = play.calculateCosts(perf.audience)

      lines += s"  ${play.name}: ${NumberFormat
          .getCurrencyInstance(culture)
          .format((performanceCost / 100).toDouble)} (${perf.audience} seats)$lineSeparator"
    }
    lines
  }

  private def createFooter(totalAmount: Int, volumeCredits: Int) = {
    val line1 =
      s"Amount owed is ${NumberFormat.getCurrencyInstance(culture).format(totalAmount / 100d)}$lineSeparator"
    val line2 = s"You earned ${volumeCredits} credits$lineSeparator"
    line1 + line2
  }

  private def calculateTotalCosts(
      invoice: Invoice,
      plays: Map[String, Play]
  ) = {
    var totalAmount = 0
    for (perf <- invoice.performances) {
      val play = plays(perf.playId)
      val performanceCost = play.calculateCosts(perf.audience)
      totalAmount += performanceCost
    }
    totalAmount
  }

  private def calculateTotalCredits(
      invoice: Invoice,
      plays: Map[String, Play]
  ) = {
    var totalCredits = 0
    for (perf <- invoice.performances) {
      val play = plays(perf.playId)

      totalCredits += play.calculateCredits(perf.audience)
    }
    totalCredits
  }
}
