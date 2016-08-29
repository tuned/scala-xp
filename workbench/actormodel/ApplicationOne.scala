package workbench.actormodel

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor._
import akka.pattern.{ ask, pipe }
import akka.util.Timeout
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._

object ApplicationOne extends App {
  // message types
  case object AreYouUp
  case class Increment(n: Int) // increment your counter by n
  case object WhatIsYourState // send back your name and counter
  
  implicit val timeout = Timeout(5 seconds)
  
  class ActorOne extends Actor {
    val name = "Actor 1"
    var state: Int = 0
    
    def receive = {
      case AreYouUp => 
        println(self, name)
        b ! AreYouUp
      case WhatIsYourState =>
        state += 1
        println("State 1: " + state)
        val result = b ? Increment(2)
        //result foreach println
        result.onComplete {
            case Success(value) => println(s"Got the callback, meaning = $value")
            case Failure(e) => e.printStackTrace
          }
        Await.result(system.terminate(), Duration(10, TimeUnit.SECONDS))
    }
    
    val b = context.actorOf(Props[ActorTwo], "actor2")
  }
  
  class ActorTwo extends Actor {
    val name = "Actor 2"
    var state: Int = 0
    
    def receive = {
      case AreYouUp => 
        println(self, name, state)
        sender ! WhatIsYourState
      case Increment(n: Int) =>
        state += n
        sender ! state
    }
  }
  
  val system = ActorSystem("this-system")
  val a = system.actorOf(Props[ActorOne], "actor1")
  
  
  a ! AreYouUp
  
  //Thread.sleep(1000)
  //system.terminate()
  
}