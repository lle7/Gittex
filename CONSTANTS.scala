package edu.towson.cis.cosc455.lle7.project1

object CONSTANTS {
  val DOCB : String = "\\BEGIN"
  val DOCE : String = "\\END"
  val TITLEB: String = "\\TITLE["
  val BRACKETE : String = "]"
  val HEADING : String = "#"
  val PARAB : String = "\\PARAB"
  val PARAE : String = "\\PARAE"
  val BOLD : String = "*"
  val LISTITEM : String = "+"
  val NEWLINE : String = "\\\\"
  val LINKB : String = "["
  val LINKE : String = "]"
  val ADDRESSB : String = "("
  val ADDRESSE : String = ")"
  val IMAGEB : String = "!["
  val DEFB : String = "\\DEF["
  val EQSIGN : String = "="
  val USEB : String = "\\USE["
  val TOKENS : String = "[]\\!#*()=+"
  val TEXT : List[String] = List("a","b","c","d","e","f","g","h","i","j","k","l","m",
    "n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M",
    "N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
  val numbersEtc : List[String] = List("1","2","3","4","5","6","7","8","9","0",
    ",",".","\"",":","?","_","/", "'", "")
  val whiteSpace : List[String] = List(" ", "\t", "\n", "\b","\f","\r")
  val validText : List[String] = whiteSpace ::: TEXT ::: numbersEtc
  val SINGLEKEYWORD : List[Char] = List('=' , '+', '#', '(', ')', '[', ']')



}
