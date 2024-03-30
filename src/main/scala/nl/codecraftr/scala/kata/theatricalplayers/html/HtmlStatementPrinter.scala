package nl.codecraftr.scala.kata.theatricalplayers.html

import nl.codecraftr.scala.kata.theatricalplayers.statements.{
  Invoice,
  Performance,
  StatementPrinter
}
import nl.codecraftr.scala.kata.theatricalplayers.formatCosts

import java.lang.System.lineSeparator

object HtmlStatementPrinter extends StatementPrinter {
  override def print(invoice: Invoice): String =
    s"""<h1>Statement for ${invoice.customer}</h1>
           |<ul>
           |${invoice.performances.map(line).mkString(lineSeparator)}
           |</ul>
           |<h2>Amount owed is ${formatCosts(invoice.totalCosts)}</h2>
           |<h2>You earned ${invoice.totalCredits} credits</h2>
           |""".stripMargin

  private def line(perf: Performance) =
    s" <li>${perf.play.name}: ${formatCosts(perf.totalCosts)} (${perf.audience} seats)</li>"
}
