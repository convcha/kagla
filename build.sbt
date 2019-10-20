import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.0.1"
ThisBuild / organization     := "kagla"
ThisBuild / organizationName := "kagla"

lazy val root = (project in file("."))
  .dependsOn(core)
  .settings(
    name := "kagla",
  )

lazy val core = project
  .settings(
    name := "core",
    libraryDependencies += scalaTest % Test
  )

lazy val genASTGraph = taskKey[Unit]("Generate AST graph.")
lazy val task = project
    .settings(
    )
  .dependsOn(core)