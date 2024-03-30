package nl.codecraftr.scala.kata.theatricalplayers.text

import nl.codecraftr.scala.kata.theatricalplayers.{
  Invoice,
  Performance,
  StatementPrinter
}

import java.lang.System.lineSeparator
import java.text.NumberFormat
import java.util.Locale

object TextStatementPrinter extends StatementPrinter {
  def print(invoice: Invoice): String =
    createHeader(invoice) ++
      createLines(invoice) ++
      createFooter(invoice)

  private def createHeader(invoice: Invoice) =
    s"Statement for ${invoice.customer}$lineSeparator"

  private def createLines(invoice: Invoice) =
    invoice.performances
      .map(line)
      .mkString("")

  private def line(perf: Performance) =
    s"  ${perf.play.name}: ${NumberFormat
        .getCurrencyInstance(Locale.US)
        .format((perf.totalCosts / 100).toDouble)} (${perf.audience} seats)$lineSeparator"

  private def createFooter(invoice: Invoice) =
    costLine(invoice) + creditLine(invoice)

  private def creditLine(invoice: Invoice) =
    s"You earned ${invoice.totalCredits} credits$lineSeparator"

  private def costLine(invoice: Invoice) =
    s"Amount owed is ${NumberFormat.getCurrencyInstance(Locale.US).format(invoice.totalCosts / 100d)}$lineSeparator"
}
