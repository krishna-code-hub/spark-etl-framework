package com.scb

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.spark.sql.{Column, DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.log4j.LogManager
import com.scb.models._
import com.scb.utils.Utilities._
import com.scb.scd._
import com.scb.configparser._

import java.io.File
import java.sql.Date

object ETLFramework {

  def main(args: Array[String]): Unit = {

    val log = LogManager.getLogger(this.getClass.getName)

    val sparkSession = createSparkSession("scdframework", "local")

    import sparkSession.implicits._
    val prevempDF = Seq(Employee(1, "krishna", "eee", "india", "software_engineer", "2022-10-01", "9999-12-31", "N")
      , Employee(2, "Preethi", "ece", "india", "software_engineer", "2022-10-01", "9999-12-31", "N")
      , Employee(3, "Vidyut", "eee", "india", "scientist", "2022-10-01", "9999-12-31", "N"),
        Employee(4, "abhishek", "cse", "india", "software_engineer", "2022-10-01", "9999-12-31", "N")).toDF()

    val newempDF = Seq(Employee(1, "krishna", "eee", "singapore", "software_engineer", "2022-10-22", "9999-12-31", "N")
      , Employee(2, "Preethi", "ece", "india", "software_engineer", "2022-10-22", "9999-12-31", "N")
      , Employee(3, "Vidyut", "eee", "india", "pilot", "2022-10-22", "9999-12-31", "N"),
        Employee(5, "prasanna", "mech", "india", "software_engineer", "2022-10-22", "9999-12-31", "N")).toDF()

    log.info("empDF")

    //ScdProcess.run(newempDF,prevempDF,Seq("id"),sparkSession)
    //Thread.sleep(1000000000)
    val yamlConfig = parse("D:\\code-repo\\scala\\scalatest11\\yaml-config.yml")
    println(yamlConfig.sparkAppConfig.appName)
    println(yamlConfig.sparkAppConfig.core.foreach(println))

    val schemacfg = Parser.schemaParse("D:\\code-repo\\etlframework\\configurations\\TableSchema.yml")
    val tablename1 = "employee"
    val column_list1 = schemacfg.tableSchema.filter(_.tablename == tablename1).map(x => x.columns).flatMap( p => p)
    column_list1.map( p => ( p.name, p.datatype)).foreach(println)
  }


  def parse(configPath: String): AppConfig = {
    val mapper = new ObjectMapper(new YAMLFactory)
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(new File(configPath), classOf[AppConfig])
  }


}


