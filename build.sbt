name := "etlframework"
version := "0.1"
scalaVersion := "2.12.6"
developers := List(
  Developer(id="kchinnad", name="Krishnamoorthy", email="", url=url("http://www.kchinnad.com"))
)

val spark_version = "3.1.3"
val scala_version =  "2.12.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % spark_version % "provided",
  "org.apache.spark" %% "spark-sql" % spark_version % "provided",
  "org.apache.spark" %% "spark-hive" % spark_version % "provided",
  "com.fasterxml.jackson.core" % "jackson-core" % scala_version)

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % scala_version
libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % scala_version
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % scala_version
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % scala_version
libraryDependencies += "org.rogach" %% "scallop" % "3.5.1"
libraryDependencies += "org.apache.logging.log4j" %% "log4j-core" % "2.18.0"
libraryDependencies += "org.apache.logging.log4j" %% "log4j-api" % "2.18.0"
libraryDependencies += "org.apache.logging.log4j" %% "log4j-api-scala_2.12" % "12.0"