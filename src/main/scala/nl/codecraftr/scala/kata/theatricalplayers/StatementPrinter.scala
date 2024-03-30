package nl.codecraftr.scala.kata.theatricalplayers

import java.lang.System.lineSeparator
import java.text.NumberFormat
import java.util.Locale

// TODO seems like an intermediate class is missing, such as Statement
class StatementPrinter {
  private val culture = Locale.US

  def print(invoice: Invoice): String = {
    // TODO this could be part of a statement printer strategy
    var result = createHeader(invoice.customer)
    result += createLines(invoice)
    result += createFooter(invoice.totalCosts, invoice.totalCredits)

    result
  }

  private def createHeader(customer: String) =
    s"Statement for $customer$lineSeparator"

  private def createLines(invoice: Invoice) =
    invoice.performances
      .map(perf => playLine(perf.play, perf.audience))
      .mkString("")

  private def playLine(play: Play, audience: Int) = {
    val performanceCost = play.calculateCosts(audience)
    val playLine = s"  ${play.name}: ${NumberFormat
        .getCurrencyInstance(culture)
        .format((performanceCost / 100).toDouble)} ($audience seats)$lineSeparator"
    playLine
  }

  private def createFooter(totalCosts: Int, volumeCredits: Int) = {
    val line1 =
      s"Amount owed is ${NumberFormat.getCurrencyInstance(culture).format(totalCosts / 100d)}$lineSeparator"
    val line2 = s"You earned $volumeCredits credits$lineSeparator"
    line1 + line2
  }
}
