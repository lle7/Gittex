package edu.towson.cis.cosc455.lle7.project1
//trait is an interface, more like a contract, must do everything in here
trait LexicalAnalyzer {
  def addChar() : Unit
  def getChar() : Unit
  def getNextToken() : Unit
  def lookup(token :String) : Boolean
}
