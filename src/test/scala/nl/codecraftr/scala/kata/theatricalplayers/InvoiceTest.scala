package nl.codecraftr.scala.kata.theatricalplayers

import nl.codecraftr.scala.kata.theatricalplayers.PlayTdb.{aComedy, aTragedy}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class InvoiceTest extends AnyFlatSpec with Matchers {
  "total costs" should "sum costs for all performances" in {
    val invoice = Invoice(
      "BigCo",
      List(
        Performance(aTragedy, 1),
        Performance(aComedy, 1),
      )
    )

    invoice.totalCosts shouldBe 70300
  }

  "total credits" should "sum credits for all performances" in {
    val invoice = Invoice(
      "BigCo",
      List(
        Performance(aTragedy, 31),
        Performance(aComedy, 5),
      )
    )

    invoice.totalCredits shouldBe 2
  }
}
