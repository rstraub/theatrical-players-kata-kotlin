package nl.codecraftr.scala.kata.theatricalplayers.text

import nl.codecraftr.scala.kata.theatricalplayers.{Invoice, Play}

import java.lang.System.lineSeparator
import java.text.NumberFormat
import java.util.Locale

class TextStatementPrinter {
  private val culture = Locale.US

  def print(invoice: Invoice, plays: Map[String, Play]): String = {
    val totalCosts = invoice.calculateCosts(plays)
    val totalCredits = invoice.calculateCredits(plays)

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

  private def createFooter(totalCosts: Int, volumeCredits: Int) = {
    val line1 =
      s"Amount owed is ${NumberFormat.getCurrencyInstance(culture).format(totalCosts / 100d)}$lineSeparator"
    val line2 = s"You earned $volumeCredits credits$lineSeparator"
    line1 + line2
  }
}
