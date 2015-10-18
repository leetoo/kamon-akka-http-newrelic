package com.gamsd.kamon.akka.http.examples

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.{ActorMaterializer, Materializer}
import kamon.trace.Tracer

import scala.concurrent.{Future, ExecutionContextExecutor}

object NewRelicExample extends KamonMonitoring with App {

  implicit val system: ActorSystem = ActorSystem("AkkaHttpSystem")
  implicit def executor: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: Materializer = ActorMaterializer()

  val routes = withKamonTracing {
    (get & path("fast")){

      Thread.sleep(10)

      val future: Future[String] = Tracer.currentContext.withNewAsyncSegment("fast-segment", "database", "database-test") {
       Future {
        Thread.sleep(10)
        "fast"
       }
      }

      complete(future)

    } ~
    (get & path("slow")){

      Thread.sleep(50)

      val res: String = Tracer.currentContext.withNewSegment("slow-segment", "http-client", "http-test") {
        Thread.sleep(50)
        "slow"
      }

      complete(res)


    } ~
    (get & path("error")){

      Thread.sleep(10)
      val failed = Future {new Exception("General Stick Expection")}

      complete(failed)
    }
  }

  val (address, port) = ("localhost", 8080)

  Http().bindAndHandle(routes, address, port)
  println(s"Application started on $address:$port")

}
