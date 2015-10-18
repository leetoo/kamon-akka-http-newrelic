enablePlugins(JavaAppPackaging)

name := "kamon-akka-http-newrelic"
organization := "com.gamsd"
version := "0.1"
scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV       = "2.3.12"
  val akkaStreamV = "1.0"
  val kamonV      = "0.5.2"

  Seq(
    "com.typesafe.akka"         %% "akka-actor"                           % akkaV,
    "com.typesafe.akka"         %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka"         %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka"         %% "akka-http-experimental"               % akkaStreamV,
    "com.typesafe.akka"         %% "akka-http-spray-json-experimental"    % akkaStreamV,
    "com.typesafe.akka"         %% "akka-http-testkit-experimental"       % akkaStreamV,
    "io.kamon"                  %% "kamon-core"                           % kamonV,
    "io.kamon"                  %% "kamon-scala"                          % kamonV,
    "io.kamon"                  %% "kamon-akka"                           % kamonV,
    "io.kamon"                  %% "kamon-system-metrics"                 % kamonV,
    "io.kamon"                  %% "kamon-statsd"                         % kamonV,
    "io.kamon"                  %% "kamon-newrelic"                       % kamonV,
    "org.aspectj"               %  "aspectjweaver"                        % "1.8.7",
    "com.newrelic.agent.java"   %  "newrelic-agent"                       % "3.21.0"
  )
}

mappings in (Universal, packageZipTarball) += file("src/main/resources/newrelic.yml") -> "newrelic.yml"

javaOptions in Universal ++= Seq(
  "-J-javaagent:lib/org.aspectj.aspectjweaver-1.8.7.jar",
  "-J-javaagent:lib/com.newrelic.agent.java.newrelic-agent-3.21.0.jar",
  "-J-Dnewrelic.config.file=newrelic.yml"
)

fork in run := true
