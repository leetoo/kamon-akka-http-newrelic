package com.gamsd.kamon.akka.http.examples

import akka.http.scaladsl.server.Directives._
import kamon.Kamon
import kamon.trace.Tracer

trait KamonMonitoring {

  Kamon.start()

  val withKamonTracing = mapInnerRoute({inner => ctx =>
    val contextName = s"${ctx.request.uri.path.toString()} - ${ctx.request.method.name}"
    Tracer.withNewContext(contextName, autoFinish = true) {
      inner(ctx)
    }
  })

}
