package com.scb

import org.apache.spark.sql.{Column, DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.log4j.LogManager

import com.scb.models._
import com.scb.utils.Utilities._
import com.scb.scd._

import java.sql.Date

object ETLFramework {

  def main(args: Array[String]): Unit = {

    val log = LogManager.getLogger(this.getClass.getName)

    val sparkSession = createSparkSession("scdframework","local")

    import sparkSession.implicits._
    val prevempDF = Seq(Employee(1,"krishna","eee","india","software_engineer","2022-10-01","9999-12-31","N")
    , Employee(2,"Preethi","ece","india","software_engineer","2022-10-01","9999-12-31","N")
    , Employee(3,"Vidyut","eee","india","scientist","2022-10-01","9999-12-31","N"),
        Employee(4,"abhishek","cse","india","software_engineer","2022-10-01","9999-12-31","N")).toDF()

    val newempDF = Seq(Employee(1,"krishna","eee","singapore","software_engineer","2022-10-22","9999-12-31","N")
      , Employee(2,"Preethi","ece","india","software_engineer","2022-10-22","9999-12-31","N")
      , Employee(3,"Vidyut","eee","india","pilot","2022-10-22","9999-12-31","N"),
      Employee(5,"prasanna","mech","india","software_engineer","2022-10-22","9999-12-31","N")).toDF()

    log.info("empDF")

    ScdProcess.run(newempDF,prevempDF,Seq("id"),sparkSession)
    //Thread.sleep(1000000000)
  }
}
