package edu.towson.cis.cosc455.lle7.project1

import scala.collection.mutable.Stack
//
class MySyntaxAnalyzer extends SyntaxAnalyzer{

  var errorFound : Boolean = false
  //Declare a stack
  var parser = Stack[String]()

  //get the current token from the file
  override def gittex(): Unit = {
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)){
      Complier.Scanner.getNextToken()
    }
    else{
      println("Error Error Error")
      System.exit(1)
    }
  }
  def doce() : Unit = {
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)){
      //add parse tree / stack
      parser.push(CONSTANTS.DOCE)
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: \\END was expected")
      System.exit(1)
    }
  }

  //format of paragraph
  override def paragraph(): Unit = {
    parab()
    variableDefine()
    while (!Complier.currentToken.equalsIgnoreCase(CONSTANTS.PARAE)){
      innerText()
    }
    parae()

  }

  def parab (): Unit = {
    //get the token for the paragraph and parse it
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)){
      //add parse tree / stack
      parser.push(CONSTANTS.PARAB)
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: \\PARAB was expected")
      System.exit(1)
    }
  }

  def parae () : Unit = {
    //Handling the paragraph ending
    //get the token for the paragraph and parse it
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.PARAE)){
      //add parse tree / stack
      parser.push(CONSTANTS.PARAE)
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: \\PARAE was expected")
      System.exit(1)
    }
  }

  override def innerItem(): Unit = {
    //look at current token, ignore if it's not USEB. If it is call the appropriate method to handle it
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.USEB)){
      variableUse()
      innerItem()
    }
    //look at current token, ignore if it's not bold. If it is call the appropriate method to handle it
    else if(Complier.currentToken.equals(CONSTANTS.BOLD)){
      bold()
      innerItem()
    }
    //look at current token, ignore if it's not linkb. If it is call the appropriate method to handle it
    else if(Complier.currentToken.equals(CONSTANTS.LINKB)){
      link()
      innerItem()
    }
    //look at current token, ignore if it's not doce. If it is call the appropriate method to handle it
    else if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)){
      errorFound = true
    }

    else  if(Complier.currentToken.equals(CONSTANTS.validText)){
      innerItem()
    }
    else{
      errorFound = true
    }
  }

  //checks if the actual text follows the syntax rules and checks the current token
  override def innerText(): Unit = {
    if(Complier.currentToken.contains(CONSTANTS.PARAE)){
      if(parser.contains(CONSTANTS.PARAB)){
        parae()
      }
      else {
        println("SYNTAX ERROR!: \\PARAB was never defined")
        System.exit(1)
      }
    }
    else if(Complier.currentToken.equals(CONSTANTS.BOLD)){
      bold()
      innerText()
    }
    else if(Complier.currentToken.equals(CONSTANTS.LINKB)){
      link()
      innerText()
    }
    else if (Complier.currentToken.equals(CONSTANTS.LISTITEM)){
      listItem()
      innerText()
    }
    else if (Complier.currentToken.equals(CONSTANTS.HEADING)){
      heading()
      innerText()
    }
    else if (Complier.currentToken.equals(CONSTANTS.IMAGEB)){
      image()
      innerText()
    }
    else if (Complier.currentToken.equals(CONSTANTS.USEB)){
      variableUse()
      innerText()
    }
    else if (Complier.currentToken.equals(CONSTANTS.NEWLINE)){
      newline()
      innerText()
    }
    else if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)){
      errorFound = true
    }
    else  if(Complier.currentToken.equals(CONSTANTS.validText)){
      innerText()
    }
    else{
      errorFound = true
    }
  }

  def text() : Unit = {
    if(Complier.currentToken.equals(CONSTANTS.validText)){
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: text was expected")
    }
  }

  override def link(): Unit = {
    LBrac()
    text()
    RBrac()
    LParen()
    text()
    RParen()
  }

  def LBrac() : Unit = {
    if (Complier.currentToken.equals(CONSTANTS.LINKB)){
      parser.push("[")
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: [ was expected")
      System.exit(1)
    }
  }
  def RBrac() : Unit = {
    if (Complier.currentToken.equals(CONSTANTS.LINKE)){
      parser.push("]")
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: ] was expected")
      System.exit(1)
    }
  }
  def LParen() : Unit = {
    if (Complier.currentToken.equals(CONSTANTS.ADDRESSB)){
      parser.push(CONSTANTS.ADDRESSB)
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: ( was expected")
      System.exit(1)
    }
  }
  def RParen() : Unit = {
    if (Complier.currentToken.equals(CONSTANTS.ADDRESSE)){
      parser.push(CONSTANTS.ADDRESSE)
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: ) was expected")
      println(Complier.currentToken + " was discovered")
      System.exit(1)
    }
  }


  override def body(): Unit = {
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)){
      paragraph()
      body()
    }
    else if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)){
      paragraph()
      body()
    }
    else if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)){
      variableDefine()
      body()
    }
    else if (Complier.currentToken.equalsIgnoreCase((CONSTANTS.NEWLINE))){
      newline()
      body()
    }
    else{
      innerText()
      body()
    }
  }

  override def bold(): Unit = {
    //look at current token, throw error if it's not bold
    if(Complier.currentToken.equals(CONSTANTS.BOLD)){
      //add parse tree / stack
      parser.push(CONSTANTS.BOLD)
      Complier.Scanner.getNextToken()
    }
    else {
      println("SYNTAX ERROR!: * was expected")
      System.exit(1)
    }
  }

  override def newline(): Unit = {
    //look at current token, throw error if it's not newline
    if (Complier.currentToken.equals(CONSTANTS.NEWLINE)){
      //add parse tree / stack
      parser.push(CONSTANTS.NEWLINE)
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: \\ was expected")
      System.exit(1)
    }
  }

  override def title(): Unit = {
    titleb()
    text()
    RBrac()
  }
  def titleb(): Unit = {
    //look at current token, throw error if it's not title beginning
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
      //add parse tree / stack
      parser.push(CONSTANTS.TITLEB)
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: \\TITLE[ was expected")
      System.exit(1)
    }
  }

  override def variableDefine(): Unit = {
    //look at current token, throw error if it's not defb
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)){
      //add parse tree / stack
      parser.push(CONSTANTS.DEFB)
      Complier.Scanner.getNextToken()
      text()
      equalSign()
      text()
      RBrac()
    }
    else{
      println("SYNTAX ERROR!: \\DEF[ was expected")
      System.exit(1)
    }
  }

  def equalSign() : Unit ={
    //look at current token, throw error if it's not equal sign
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.EQSIGN)){
      //add parse tree / stack
      parser.push(CONSTANTS.EQSIGN)
      Complier.Scanner.getNextToken()
    }
    else{
      println("SYNTAX ERROR!: = was expected")
      System.exit(1)
    }
  }

  override def image(): Unit = {
    //look at current token, throw error if it's not image beginning
    if(Complier.currentToken.equals(CONSTANTS.IMAGEB)){
      //add parse tree / stack
      parser.push(CONSTANTS.IMAGEB)
      Complier.Scanner.getNextToken()
      text()
      RBrac()
      LParen()
      text()
      RParen()
    }
    else{
      println("SYNTAX ERROR!: ![ was expected")
      System.exit(1)
    }
  }

  override def variableUse(): Unit = {
    //look at current token, throw error if it's not useeb
    if(Complier.currentToken.equalsIgnoreCase(CONSTANTS.USEB)){
      //add parse tree / stack
      parser.push(CONSTANTS.USEB)
      Complier.Scanner.getNextToken()
      text()
      RBrac()
    }
    else{
      println("SYNTAX ERROR!: \\USE[ was expected")
      System.exit(1)
    }
  }

  override def heading(): Unit = {
    //look at current token, throw error if it's not heading
    if(Complier.currentToken.equals(CONSTANTS.HEADING)){
      //add parse tree / stack
      parser.push(CONSTANTS.HEADING)
      Complier.Scanner.getNextToken()
      text()
    }
    else{
      println("SYNTAX ERROR!: # was expected")
      System.exit(1)
    }
  }

  override def listItem(): Unit = {
    //look at current token, throw error if it's not listitem
    if(Complier.currentToken.equals(CONSTANTS.LISTITEM)){
      //add parse tree / stack
      parser.push(CONSTANTS.LISTITEM)
      Complier.Scanner.getNextToken()
      innerItem()
    }
    else{
      println("SYNTAX ERROR!: + was expected")
      System.exit(1)

    }
  }
}
