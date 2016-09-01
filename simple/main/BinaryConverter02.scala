package simple.main

object BinaryConverter02 {
  var base: Int = 455
  var result = List[Int]()
  var remainder: Int = base
  var done: Boolean = false
  
  def getRemainder: Int = {
    return base.%(2)
  }
  
  def addResult(i: Int) {
    if (i == 2) {
      result ::= 0
      result ::= 1
      done = true
    }
    else if (i == 3) {
      result ::= 1
      result ::= 1
      done = true
    }
    else result ::= getRemainder   
  }
  
  def rebase = {
    base = base./(2)
  }

  def main(args: Array[String]): Unit = {  
    while (!done) {
      println(base)
      addResult(base)
      rebase
      println(base)
    }
 
    // .mkString("").toInt
    println(result.mkString("").toInt)
   }
}