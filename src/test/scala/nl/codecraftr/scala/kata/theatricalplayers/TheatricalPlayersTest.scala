package nl.codecraftr.scala.kata.theatricalplayers

import nl.codecraftr.scala.kata.theatricalplayers.html.HtmlStatementPrinter
import nl.codecraftr.scala.kata.theatricalplayers.text.TextStatementPrinter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TheatricalPlayersTest extends AnyFlatSpec with Matchers {
  "theatrical players" should "print a statement in html" in {
    val statement = TheatricalPlayers.run(HtmlStatementPrinter)

    statement shouldBe
      """<h1>Statement for BigCo</h1>
              |<ul>
              | <li>Hamlet: $400.00 (1 seats)</li>
              |</ul>
              |<h2>Amount owed is $400.00</h2>
              |<h2>You earned 0 credits</h2>
              |""".stripMargin
  }

  it should "print a statement in text" in {
    val statement = TheatricalPlayers.run(TextStatementPrinter)

    statement shouldBe
      """Statement for BigCo
            |  Hamlet: $400.00 (1 seats)
            |Amount owed is $400.00
            |You earned 0 credits
            |""".stripMargin
  }
}
