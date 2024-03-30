package nl.codecraftr.scala.kata

import java.text.NumberFormat
import java.util.Locale

package object theatricalplayers {
  def formatCosts(costs: Int): String =
    NumberFormat.getCurrencyInstance(Locale.US).format(costs / 100d)
}
