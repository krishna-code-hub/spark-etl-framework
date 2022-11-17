package com.scb.utils

import org.apache.spark.sql.{Column, DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, column, concat_ws, md5}
import org.apache.log4j.LogManager
import com.scb.utils.Constants._
import java.util.UUID

object Utilities {

  val log = LogManager.getLogger(this.getClass.getName)



  def createSparkSession(appName: String, master: String): SparkSession = {

    val sparkSessionBuilder = SparkSession.builder()
                                          .appName(appName)
                                          .config("spark.sql.adaptive.enabled","true")
                                          .config("spark.sql.shuffle.partitions","10")

    log.info(s"Master: ${master}")
    master match {
      case "local" => sparkSessionBuilder.master(master).getOrCreate()
      case _ => sparkSessionBuilder.enableHiveSupport().getOrCreate()
    }
  }

  def getHashedDF( dataframe :DataFrame, primaryKey: Seq[String]):DataFrame = {
    val allColumnsList: Seq[String] = dataframe.columns.toSeq
    val nonPkColList: Seq[String] = allColumnsList diff primaryKey
    val hashColumnsListString: Seq[String] = nonPkColList diff std_column
    val hashColumnsList: Seq[Column] = hashColumnsListString.map( c=> column(c))
    dataframe.withColumn("hashed", md5(concat_ws("",hashColumnsList: _*)))

}
  def getRenamedDF(dataframe :DataFrame, primaryKey: Seq[String], appendString: String):DataFrame = {
    val nonPkColList = dataframe.columns.toSeq diff primaryKey
    val nonPkColListRenamed = nonPkColList.map(c => appendString + c)
    val allColListRenamed = primaryKey ++ nonPkColListRenamed
    dataframe.toDF(allColListRenamed: _*)
  }

  //Generate UUID for the jobs
  def generateUUID(): String = {
    val uuid: UUID = UUID.randomUUID();
    uuid.toString;
  }

}
