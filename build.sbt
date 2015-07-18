lazy val sbtTestService =
  (project in file(".")).
  settings(
    scalacOptions := Seq("-feature"),
    name := "sbt-test-service",
    organization := "com.github.lannyripple",
    version := "0.1.0",
    sbtPlugin := true
  )
