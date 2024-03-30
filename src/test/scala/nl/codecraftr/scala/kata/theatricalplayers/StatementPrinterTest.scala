package nl.codecraftr.scala.kata.theatricalplayers

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class StatementPrinterTest extends AnyFunSuite with Matchers {
  test("statement example") {
    val invoice = Invoice(
      "BigCo",
      List(
        Performance(Play("Hamlet", "tragedy"), 55),
        Performance(Play("As You Like It", "comedy"), 35),
        Performance(Play("Othello", "tragedy"), 40)
      )
    )

    val statementPrinter = new StatementPrinter()
    val result = statementPrinter.print(invoice)

    result shouldBe
      """Statement for BigCo
              |  Hamlet: $650.00 (55 seats)
              |  As You Like It: $580.00 (35 seats)
              |  Othello: $500.00 (40 seats)
              |Amount owed is $1,730.00
              |You earned 47 credits
              |""".stripMargin
  }

  test("statement with new play types") {
    val invoice = Invoice(
      "BigCoII",
      List(
        Performance(Play("Henry V", "history"), 53),
        Performance(Play("As You Like It", "pastoral"), 55)
      )
    )

    val statementPrinter = new StatementPrinter()
    assertThrows[Exception] {
      statementPrinter.print(invoice)
    }
  }
}
