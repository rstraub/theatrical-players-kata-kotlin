package nl.codecraftr.scala.kata.theatricalplayers.text

import nl.codecraftr.scala.kata.theatricalplayers.statements.{
  Comedy,
  History,
  Invoice,
  Pastoral,
  Performance,
  Tragedy
}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TextStatementPrinterTest extends AnyFunSuite with Matchers {
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

    val result = TextStatementPrinter.print(invoice)

    result shouldBe
      """Statement for BigCo
              |  Hamlet: $650.00 (55 seats)
              |  As You Like It: $580.00 (35 seats)
              |  Othello: $500.00 (40 seats)
              |  Henry V: $630.00 (53 seats)
              |  As You Like It: $650.00 (55 seats)
              |Amount owed is $3,010.00
              |You earned 95 credits
              |""".stripMargin
  }
}
