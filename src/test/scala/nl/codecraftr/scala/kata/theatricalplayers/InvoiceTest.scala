package nl.codecraftr.scala.kata.theatricalplayers

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class InvoiceTest extends AnyFlatSpec with Matchers {
  "total costs" should "sum costs for all performances" in {
    val invoice = Invoice(
      "BigCo",
      List(
        Performance(Tragedy("Hamlet"), 1),
        Performance(History("Henry V"), 1),
      )
    )

    invoice.totalCosts shouldBe 40000 * 2
  }

  "total credits" should "sum credits for all performances" in {
    val invoice = Invoice(
      "BigCo",
      List(
        Performance(Tragedy("Hamlet"), 31),
        Performance(History("Henry V"), 31),
      )
    )

    invoice.totalCredits shouldBe 2
  }
}
