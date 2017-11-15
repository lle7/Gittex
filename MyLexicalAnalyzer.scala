package edu.towson.cis.cosc455.lle7.project1

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class MyLexicalAnalyzer extends LexicalAnalyzer{
  val lexemes = Array("\\BEGIN","\\END","\\TITLE[","]","#","\\PARAB",
    "\\PARAE","\\DEF[","\\USE[","*","+","\\\\","[","(",")","=","![","]",CONSTANTS.validText)
  var token = new ArrayBuffer[Char](70)
  var sourceFile : Array[Char] = Array()
  var position = 0
  var nextChar = ' '
  var fileSize = 0
  var fileLocation = 0

  def start(file: String): Unit = {
    sourceFile = file.toCharArray
    fileSize = sourceFile.length - 1
  }
  //get another character
  override def addChar(): Unit = {
    token += nextChar
  }

  //collects the character data
  override def getChar(): Unit = {
    if(position < fileSize) {
      position += 1
      nextChar = sourceFile.charAt(position)
    }
    else{
      return
    }
  }
  //this method does a character by character analysis to get the next token
  override def getNextToken(): Unit = {
    getChar()
    getNonText()

    //process single character lexeme i.e ()[]+#=
    if(nextChar.equals(CONSTANTS.SINGLEKEYWORD)){
      addChar()
      getChar()
    }
      //handling title def use lexeme
    else if(nextChar.equals('\\')){
      addChar()
      getChar()
      while(!nextChar.equals('[')&& !nextChar.equals(' ')&& !nextChar.equals('\n')){
          addChar()
          getChar()
      }
      if(nextChar.equals('[')){
        addChar()
        getChar()
        group()
      }
    }
      //handling image lexeme
    else if(nextChar.equals('!')){
      addChar()
      getChar()
      if (nextChar.equals('[')){
        addChar()
      }
    }
      //handling bold lexeme
    else if(nextChar.equals('*')){
      addChar()
      getChar()
      if(nextChar.equals('*')){
        addChar()
        getChar()
        group()
      }
    }
      //everything else i.e. text
    else{
      addChar()
      getChar()
      while (nextChar.equals(CONSTANTS.validText)){
        addChar()
      }
    }
    group()
  }


  override def lookup(candidateToken :String): Boolean = {
    if(candidateToken.equals(lexemes)){
      return true
    }
    else
      return false
  }
  //get the white spaces and non character
  def getNonText () : Unit = {
    while(nextChar.equals(CONSTANTS.whiteSpace)){
      getChar()
    }
  }

  //Set the current token to move from one to another
  def setCurrentToken(currentToken : String) : Unit ={
    Complier.currentToken = currentToken
  }
  //group the char then output it
  def group() : Unit = {
    val vaildToken: String = token.mkString
    //if match with the token in array print it out
    if (lexemes.contains(vaildToken)) {
    println(vaildToken)
      setCurrentToken(vaildToken)
    token.clear()
    }
    else if(!vaildToken.contains("\\")){
      println(vaildToken)
      setCurrentToken(vaildToken)
      token.clear()
    }
    else{
      println("Lexical error")
      System.exit(1)
    }
  }
}
