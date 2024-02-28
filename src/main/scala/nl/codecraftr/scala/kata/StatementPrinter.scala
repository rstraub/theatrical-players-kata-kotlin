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
    var volumeCredits = 0

    for (perf <- invoice.performances) {
      val play = plays(perf.playId)
      var thisAmount = 0

      play.`type` match {
        case "tragedy" => {
          thisAmount = 40000
          if (perf.audience > 30)
            thisAmount += 1000 * (perf.audience - 30)
        }
        case "comedy" => {
          thisAmount = 30000
          if (perf.audience > 20)
            thisAmount += 10000 + 500 * (perf.audience - 20)
          thisAmount += 300 * perf.audience
        }
        case _ => throw new Exception("unknown type: " + play.`type`)
      }
      totalAmount += thisAmount;

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0)
      // add extra credit for every ten comedy attendees
      if ("comedy" == play.`type`)
        volumeCredits += Math.floor(perf.audience / 5d).toInt

      // print line for this order
      result += s"  ${play.name}: ${NumberFormat
          .getCurrencyInstance(culture)
          .format((thisAmount / 100).toDouble)} (${perf.audience} seats)$lineSeparator"
    }

    result += createFooter(totalAmount, volumeCredits)

    result
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
