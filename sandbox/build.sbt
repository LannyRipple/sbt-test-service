lazy val sandbox =
  (project in file(".")).
  enablePlugins(SbtTestService).
  settings(
    testServiceScript := "bin/service",
    test in Test <<= (test in Test).dependsOn(testServiceStart),
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % "3.6.2" % "test"
    )
  )
