name := "etlframework"
version := "0.1"
scalaVersion := "2.12.17"
developers := List(
  Developer(id="kchinnad", name="Krishnamoorthy", email="", url=url("http://www.kchinnad.com"))
)

val spark_version = "3.1.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % spark_version % "provided",
  "org.apache.spark" %% "spark-sql" % spark_version % "provided",
  "org.apache.spark" %% "spark-hive" % spark_version % "provided")