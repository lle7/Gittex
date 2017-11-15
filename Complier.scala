package edu.towson.cis.cosc455.lle7.project1
import java.io.{File, IOException}
import java.io._
object Complier {

  var currentToken : String = ""
  var fileContents : String = ""

  val Scanner = new MyLexicalAnalyzer
  val Parser = new MySyntaxAnalyzer
  val SemanticAnalyzer = new MySemanticAnalyzer

  def main(args: Array[String]): Unit = {
    checkFile(args)
    readFile(args(0))

    println("Original file: ")
    println(fileContents)
    println("Analyzing the file:")
    println()

    //scan the lexical analyzer
    Scanner.start(fileContents)
    while(Scanner.fileLocation < Scanner.fileSize)
    {
      //get the next token
      Scanner.getNextToken()
      //Syntax Analyzer
      Parser.gittex()
    }
    SemanticAnalyzer.semantic()
  }
  def readFile(file : String) = {
    val source = scala.io.Source.fromFile(file)
    fileContents = try source.mkString finally source.close()
  }

  def checkFile(args: Array[String]) = {
    if(args.length != 1){
      println("USAGE ERROR: wrong number of args")
      System.exit(1)
    }
    else if (! args(0).endsWith(".txt")){
      println("USAGE ERROR: wrong extension!")
      System.exit(1)
    }
  }
}
