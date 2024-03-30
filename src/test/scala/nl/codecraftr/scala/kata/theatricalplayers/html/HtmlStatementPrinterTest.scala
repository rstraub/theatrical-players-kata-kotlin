package nl.codecraftr.scala.kata.theatricalplayers.html

import nl.codecraftr.scala.kata.theatricalplayers._
import nl.codecraftr.scala.kata.theatricalplayers.statements.{Comedy, History, Invoice, Pastoral, Performance, Tragedy}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class HtmlStatementPrinterTest extends AnyFunSuite with Matchers {
  test("statement example") {
    val invoice = Invoice(
      "BigCo",
      List(
        Performance(Tragedy("Hamlet"), 55),
        Performance(Comedy("As You Like It"), 35),
        Performance(Tragedy("Othello"), 40),
        Performance(History("Henry V"), 53),
        Performance(Pastoral("As You Like It"), 55)
      )
    )

    val result = HtmlStatementPrinter.print(invoice)

    result shouldBe
      """<h1>Statement for BigCo</h1>
        |<ul>
        | <li>Hamlet: $650.00 (55 seats)</li>
        | <li>As You Like It: $580.00 (35 seats)</li>
        | <li>Othello: $500.00 (40 seats)</li>
        | <li>Henry V: $630.00 (53 seats)</li>
        | <li>As You Like It: $650.00 (55 seats)</li>
        |</ul>
        |<h2>Amount owed is $3,010.00</h2>
        |<h2>You earned 95 credits</h2>
        |""".stripMargin
  }
}
