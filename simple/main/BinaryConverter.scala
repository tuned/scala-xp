package simple.main

object BinaryConverter {
  var integer: Int = 2
  var binary = List[Int]()
  var done: Boolean = false
  println(integer)
  
  def nextDigit = {
    integer = (integer./(2))    
  }
  
  def remainder(i: Int): Int = {
    // divide by 2
    // take the modulo and attach it to the result
    if (integer == 1) {
      return integer
    }
    if (integer == 0) {
      done = true
      return integer
    }
    var c = integer.%(2)
    println("c= " + c)
    nextDigit
    println("nextDigit= " + integer)
    return c 
  }

  def main(args: Array[String]): Unit = {  
    while (!done) {
      binary ::= remainder(integer)   
    }
 
    // .mkString("").toInt
    println(binary.reverse)
   }
}