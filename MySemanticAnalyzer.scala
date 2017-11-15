package edu.towson.cis.cosc455.lle7.project1
import scala.collection.mutable.Stack
import java.awt.Desktop
import java.io.{File, IOException}
class MySemanticAnalyzer {
  var html = Stack[String]()
  var parser = Stack[String]()
  var nextToken = ""
  //Use the stack parser from the syntaxanalyzer, continue to pop until you see keyword
  //push it into another stack with html format
  def semantic(): Unit = {
    //preps for method to reverse
    parser = Complier.Parser.parser.reverse
    nextToken = parser.pop()

    //calls method that process the files
    convertToHtml()
  }
  def convertToHtml() : Unit ={
    if(nextToken.equalsIgnoreCase(CONSTANTS.DOCB)){
      html.push("<html>")
      nextToken = parser.pop()
    }
    else if(nextToken.equalsIgnoreCase(CONSTANTS.DOCE)){
      html.push("</html>")
    }
    else if(nextToken.equalsIgnoreCase(CONSTANTS.TITLEB)){
      html.push("<head>")
      html.push("<title>")
      html.push(parser.pop())
      html.push("</title>")
      html.push("</head>")
      parser.pop()
      nextToken = parser.pop()
    }
    else if(nextToken.equalsIgnoreCase(CONSTANTS.HEADING)){
      html.push("<h>")
      html.push(parser.pop())
      html.push("</h")
      nextToken = parser.pop()
    }
    else if(nextToken.equalsIgnoreCase(CONSTANTS.PARAB)) {
      html.push("<p>")
      nextToken = parser.pop()
    }
    else if (nextToken.equalsIgnoreCase(CONSTANTS.PARAE)){
      html.push("</p>")
      nextToken = parser.pop()
    }
    else if(nextToken.equalsIgnoreCase(CONSTANTS.BOLD)){
      html.push("<b>")
      html.push(parser.pop())
      html.push("</b>")
      parser.pop()
      nextToken = parser.pop()
    }
    else if(nextToken.equalsIgnoreCase(CONSTANTS.LISTITEM)){
      html.push("<li>")
      html.push(parser.pop())
      html.push("</li>")
      nextToken = parser.pop()
    }
    else if(nextToken.equals(CONSTANTS.NEWLINE)){
      html.push("<br>")
      nextToken = parser.pop()
    }
    else if (nextToken.equalsIgnoreCase(CONSTANTS.LINKB)){
      html.push("<a herf=\"")
      html.push(nextToken)
      html.push("\" > ")
      html.push(nextToken)
      html.push("</a>")
      nextToken = parser.pop()
    }
    else if(nextToken.equalsIgnoreCase(CONSTANTS.IMAGEB)){
      val temp = parser.pop()
      html.push("<img src=\"")
      html.push(parser.pop())
      html.push("\" alt=\"")
      html.push(temp)
      html.push("\">")
      nextToken = parser.pop()
    }
    //vardef
    //varuse
  }

  /* * Hack Scala/Java function to take a String filename and open in default web browswer. */
  def openHTMLFileInBrowser(htmlFileStr : String) = {
    val file : File = new File(htmlFileStr.trim)
    println(file.getAbsolutePath)
    if (!file.exists())
      sys.error("File " + htmlFileStr + " does not exist.")

    try {
      Desktop.getDesktop.browse(file.toURI)
    }
    catch {
      case ioe: IOException => sys.error("Failed to open file:  " + htmlFileStr)
      case e: Exception => sys.error("He's dead, Jim!")
    }
  }


}
