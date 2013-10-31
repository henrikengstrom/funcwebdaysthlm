package controllers

import play.api.mvc.{Controller, WebSocket}
import play.api.libs.iteratee._
import scala.concurrent.Future
import akka.actor._
import akka.pattern.ask
import scala.concurrent.duration._
import akka.util.Timeout
import actors.TwitterActor

object Application extends Controller {
  def ws() = WebSocket.async[String] { req => 
    ClientController.join()         
  }    
}

object ClientController { 
  import actors.TwitterActor._
  import scala.concurrent.ExecutionContext.Implicits.global
  implicit val timeout = Timeout(1 seconds)
  val system = ActorSystem("ClientAS")
    
  def join(): Future[(Iteratee[String, _], Enumerator[String])] = {
    (system.actorOf(Props[TwitterActor]) ? Initialize).map{
      case Connection(ref, e) => 
        (Iteratee.foreach[String] { s => 
          ref ! TwitterRequest(s) } map { _ =>  
            ref ! PoisonPill }, e) }
  }    
}


