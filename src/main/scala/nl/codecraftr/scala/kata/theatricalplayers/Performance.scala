package nl.codecraftr.scala.kata.theatricalplayers

final case class Performance(play: Play, audience: Int) {
  lazy val totalCosts: Int = play.calculateCosts(audience)
  lazy val totalCredits: Int = play.calculateCredits(audience)
}
