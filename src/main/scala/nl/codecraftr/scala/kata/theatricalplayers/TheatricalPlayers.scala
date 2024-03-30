package nl.codecraftr.scala.kata.theatricalplayers

import nl.codecraftr.scala.kata.theatricalplayers.html.HtmlStatementPrinter
import nl.codecraftr.scala.kata.theatricalplayers.statements.{Invoice, Performance, StatementPrinter, Tragedy}
import nl.codecraftr.scala.kata.theatricalplayers.text.TextStatementPrinter

object TheatricalPlayers extends App {
  private lazy val fakeInvoice = Invoice(
    "BigCo",
    List(
      Performance(Tragedy("Hamlet"), 1)
    )
  )

  def run(printer: StatementPrinter) = printer.print(fakeInvoice)

  val res =
    args match {
      case Array("html") => run(HtmlStatementPrinter)
      case Array("text") => run(TextStatementPrinter)
      case _             => run(TextStatementPrinter)
    }

  println(res)
}
