package nl.codecraftr.scala.kata.theatricalplayers

import nl.codecraftr.scala.kata.theatricalplayers.PlayTdb.{aComedy, aTragedy}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class PerformanceTest
    extends AnyFlatSpec
    with Matchers
    with TableDrivenPropertyChecks {
  "total costs" should "be 40000 for a non-comedy given an audience of 30 or less" in {
    val nonComedy = Table(
      ("play", "audience"),
      (aTragedy, 30),
      (Pastoral("x"), 1),
      (History("x"), 1)
    )

    forAll(nonComedy) { (play, audience) =>
      val performance = Performance(play, audience)
      performance.totalCosts shouldBe 40000
    }
  }

  it should "be 40000 plus 1000 per person over 30 for non-comedy" in {
    val nonComedy = Table(
      ("play", "audience", "expectedCosts"),
      (aTragedy, 31, 41000),
      (Pastoral("x"), 40, 50000),
      (History("x"), 35, 45000)
    )

    forAll(nonComedy) { (play, audience, expected) =>
      val performance = Performance(play, audience)
      performance.totalCosts shouldBe expected
    }
  }

  it should "be 30000 for comedy plus 300 per person given an audience of 20 or less" in {
    val comedy = Table(
      ("audience", "expectedCosts"),
      0 -> 30000,
      1 -> 30300,
      20 -> 36000
    )

    forAll(comedy) { (audience, expected) =>
      val performance = Performance(aComedy, audience)
      performance.totalCosts shouldBe expected
    }
  }

  it should "be 30000 and 300 for the first 20 persons in addition to 10000 plus 800 per person over 20 for comedy" in {
    val comedy = Table(
      ("audience", "expectedCosts"),
      21 -> 46800,
      22 -> 47600
    )

    forAll(comedy) { (audience, expected) =>
      val performance = Performance(aComedy, audience)
      performance.totalCosts shouldBe expected
    }
  }

  "total credits" should "calculate credits for a performance" in {

  }
}
