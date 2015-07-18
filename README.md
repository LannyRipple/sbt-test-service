# sbt-test-service Plugin

This plugin is designed to simplify testing that needs an external service
to be running.  Rather than encode an sbt plugin for each service this
plugin calls an external script to start and stop the needed service(s).

# Setup

Include the following in your project (e.g., project/sbt-test-service.sbt)

```scala
addSbtPlugin("com.github.lannyripple" % "sbt-test-service" % "0.1.0")
```

At this time the plugin is not provided by repository.  Clone the code
and 'sbt publish' or 'sbt publish-local' to make it available.

# Usage

Enable the plugin for your project and supply the desired testServiceScript setting.
You also need to supply a bit of boilerplate which cannot be supplied by the plugin
to have your tests invoke the testServiceScript.

```scala
lazy val myProject =
  (project in file(".")).
  enablePlugins(SbtTestService).
  settings(
    testServiceScript := "bin/service",
    test in Test <<= (test in Test).dependsOn(testServiceStart)
  )
```

After compile in Test finishes and before testing begins the testServiceScript
will be invoked with an argument of "start".  At test cleanup the testServiceScript
will be invoked with an argument of "stop".

# Example

The directory name `sandbox` in this project is a small sbt project which
demonstrates the use of sbt-test-service.
