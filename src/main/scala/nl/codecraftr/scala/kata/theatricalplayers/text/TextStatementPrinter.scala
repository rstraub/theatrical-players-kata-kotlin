package nl.codecraftr.scala.kata.theatricalplayers.text

import nl.codecraftr.scala.kata.theatricalplayers.statements.{
  Invoice,
  Performance,
  StatementPrinter
}
import nl.codecraftr.scala.kata.theatricalplayers.formatCosts

import java.lang.System.lineSeparator

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
    s"  ${perf.play.name}: ${formatCosts(perf.totalCosts)} (${perf.audience} seats)$lineSeparator"

  private def createFooter(invoice: Invoice) =
    costLine(invoice) + creditLine(invoice)

  private def creditLine(invoice: Invoice) =
    s"You earned ${invoice.totalCredits} credits$lineSeparator"

  private def costLine(invoice: Invoice) =
    s"Amount owed is ${formatCosts(invoice.totalCosts)}$lineSeparator"
}
