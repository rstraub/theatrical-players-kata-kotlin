package nl.codecraftr.scala.kata

import java.lang.System.lineSeparator
import java.text.NumberFormat
import java.util.Locale

final case class Invoice(customer: String, performances: List[Performance])
final case class Performance(playId: String, audience: Int)
final case class Play(name: String, `type`: String)

class StatementPrinter {
  private val culture = Locale.US

  def print(invoice: Invoice, plays: Map[String, Play]): String = {
    var result = createHeader(invoice)

    var totalAmount = 0
    for (perf <- invoice.performances) {
      val play = plays(perf.playId)
      val performanceCost = calculatePerformanceCost(play, perf)
      totalAmount += performanceCost

      // print line for this order
      result += s"  ${play.name}: ${NumberFormat
          .getCurrencyInstance(culture)
          .format((performanceCost / 100).toDouble)} (${perf.audience} seats)$lineSeparator"
    }

    val totalCredits = calculateTotalCredits(invoice, plays)

    result += createFooter(totalAmount, totalCredits)

    result
  }

  private def calculatePerformanceCost(play: Play, perf: Performance) = {
    var performanceCost = 0

    play.`type` match {
      case "tragedy" => {
        performanceCost = 40000
        if (perf.audience > 30)
          performanceCost += 1000 * (perf.audience - 30)
      }
      case "comedy" => {
        performanceCost = 30000
        if (perf.audience > 20)
          performanceCost += 10000 + 500 * (perf.audience - 20)
        performanceCost += 300 * perf.audience
      }
      case _ => throw new Exception("unknown type: " + play.`type`)
    }
    performanceCost
  }

  private def calculateTotalCredits(
      invoice: Invoice,
      plays: Map[String, Play]
  ) = {
    var totalCredits = 0
    for (perf <- invoice.performances) {
      val play = plays(perf.playId)

      // add volume credits
      totalCredits += Math.max(perf.audience - 30, 0)
      // add extra credit for every ten comedy attendees
      if ("comedy" == play.`type`)
        totalCredits += Math.floor(perf.audience / 5d).toInt
    }
    totalCredits
  }

  private def createHeader(invoice: Invoice) =
    s"Statement for ${invoice.customer}$lineSeparator"

  private def createFooter(totalAmount: Int, volumeCredits: Int) = {
    val line1 =
      s"Amount owed is ${NumberFormat.getCurrencyInstance(culture).format(totalAmount / 100d)}$lineSeparator"
    val line2 = s"You earned ${volumeCredits} credits$lineSeparator"
    line1 + line2
  }
}
