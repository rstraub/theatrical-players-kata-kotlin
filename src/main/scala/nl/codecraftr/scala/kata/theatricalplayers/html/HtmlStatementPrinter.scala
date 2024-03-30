package nl.codecraftr.scala.kata.theatricalplayers.html

import nl.codecraftr.scala.kata.theatricalplayers.{Invoice, Performance, StatementPrinter}

import java.lang.System.lineSeparator
import java.text.NumberFormat
import java.util.Locale

object HtmlStatementPrinter extends StatementPrinter {
  override def print(invoice: Invoice): String =
    s"""<h1>Statement for ${invoice.customer}</h1>
           |<ul>
           |${invoice.performances.map(line).mkString(lineSeparator)}
           |</ul>
           |<h2>Amount owed is ${NumberFormat
        .getCurrencyInstance(Locale.US)
        .format(invoice.totalCosts / 100d)}</h2>
           |<h2>You earned ${invoice.totalCredits} credits</h2>
           |""".stripMargin

  private def line(perf: Performance) =
    s" <li>${perf.play.name}: ${NumberFormat
        .getCurrencyInstance(Locale.US)
        .format((perf.totalCosts / 100).toDouble)} (${perf.audience} seats)</li>"
}
