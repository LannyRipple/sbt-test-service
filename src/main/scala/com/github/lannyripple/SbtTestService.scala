package com.github.lannyripple

import scala.language.postfixOps

import java.io.File

import sbt._
import Keys._

object SbtTestService extends AutoPlugin {

  object autoImport {
    val testServiceScript = SettingKey[String]("test-service-script")
    val testServiceStart = TaskKey[Unit]("test-service-start")
    val testServiceStop = TaskKey[Unit]("test-service-stop")
  }

  import autoImport._

  override lazy val projectSettings = Seq(
    testServiceStart := {
      invokeTestService(testServiceScript.value, "start")
    },
    testServiceStart <<= testServiceStart.dependsOn(compile in Test),
    testServiceStop := {
      invokeTestService(testServiceScript.value, "stop")
    },
    testOptions in Test += Tests.Cleanup(() => invokeTestService(testServiceScript.value, "stop"))
  )

  def invokeTestService(script: String, action: String): Int = {
    if (!new File(script).exists)
      error("no such file or directory")

    val rv = s"$script $action" !

    if (rv == 0) 0 else error("non-zero exit status")
  }
}
