package actors

import akka.actor._
import scala.concurrent.duration._
import play.api.libs.iteratee._

class TwitterActor extends Actor {
  import TwitterActor._
  import scala.concurrent.ExecutionContext.Implicits.global
    
  val (e, channel) = Concurrent.broadcast[String] 
    
  var searchTerm: Option[String] = None
    
  case object SimulateTwitterResult    
  
  val simulator = context.system.scheduler.schedule(
    1 seconds,
    2 seconds,
    self,
    SimulateTwitterResult)    
    
  override def postStop(): Unit = {
    simulator.cancel()
  }
    
  def receive = {
    case Initialize => sender ! Connection(self, e)            
    case TwitterRequest(s) => searchTerm = Some(s) 
    case SimulateTwitterResult =>
      for { 
        s <- searchTerm 
      } yield 
        channel.push("Found result for " + s + " @ " + System.currentTimeMillis)
  }    
}

object TwitterActor {    
  case object Initialize 
  case class TwitterRequest(search: String)   
  case class Connection(ref: ActorRef, e: Enumerator[String])     
}