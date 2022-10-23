package com.scb.scd

import com.scb.utils.Utilities._
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK
import com.scb.utils.Constants._

object ScdProcess {

  def run(newDF:DataFrame, prevDF:DataFrame, primaryKey: Seq[String],spark: SparkSession ) = {

    //TODO It should be changed to Batch Date
    val RECORD_CLOSE_DATE = "2022-10-22"

    val PrimaryCols = primaryKey.map(pk => column(pk))

    val HashedNewDF = getHashedDF(newDF,primaryKey)
    val HashedPreVDF = getHashedDF(prevDF,primaryKey)

    val renamedHashedPreVDF = getRenamedDF(HashedPreVDF,primaryKey,"__prev__")
    //renamedHashedPreVDF.show(false)

    //val renamedHashednewDF = getRenamedDF(HashedNewDF,primaryKey,"new_")
    //renamedHashednewDF.show(false)

    val joinedDF = renamedHashedPreVDF.join(HashedNewDF,primaryKey,"full").persist(MEMORY_AND_DISK)
    //joinedDF.show(false)

    val afterUpdatedRecords = joinedDF
      .where(renamedHashedPreVDF("__prev__hashed") =!= HashedNewDF("hashed"))
      .selectExpr(HashedNewDF.columns.toSeq: _*)

    val beforeUpdatedRecords = joinedDF
      .where(renamedHashedPreVDF("__prev__hashed") =!= HashedNewDF("hashed"))
      .selectExpr(renamedHashedPreVDF.columns.toSeq: _*)
      .drop("__prev__" + END_DATE_COLUMN_NAME)
      .withColumn("__prev__" + END_DATE_COLUMN_NAME,lit(RECORD_CLOSE_DATE))

    val dfColNames = beforeUpdatedRecords.columns.toSeq.map( c => c.replace("__prev__",""))

    val renamedBeforeUpdatedRecords = beforeUpdatedRecords.toDF(dfColNames: _*).selectExpr(afterUpdatedRecords.columns.toSeq: _*)

    //renamedBeforeUpdatedRecords.show(false)

    val noChangeRecords = joinedDF
      .where(renamedHashedPreVDF("__prev__hashed") === HashedNewDF("hashed"))
      .selectExpr(renamedHashedPreVDF.columns.toSeq: _*)
      .toDF(HashedPreVDF.columns.toSeq: _*)

    //noChangeRecords.show(false)

    val newRecords = joinedDF
      .where(renamedHashedPreVDF("__prev__hashed").isNull)
      .selectExpr(HashedNewDF.columns.toSeq: _*)

    //newRecords.show(false)


    val deletedRecords = joinedDF
      .where(HashedNewDF("hashed").isNull)
      .selectExpr(renamedHashedPreVDF.columns.toSeq: _*)
      .drop("__prev__" + DELETE_COLUMN_NAME,"__prev__" + END_DATE_COLUMN_NAME)
      .withColumn("__prev__" + DELETE_COLUMN_NAME,lit("Y"))
      .withColumn("__prev__" + END_DATE_COLUMN_NAME,lit(RECORD_CLOSE_DATE))


    val colNamed = deletedRecords.columns.toSeq.map( c => c.replace("__prev__",""))

    val renamedDeletedRecords = deletedRecords.toDF(colNamed: _*).selectExpr(afterUpdatedRecords.columns.toSeq: _*)

    //renamedDeletedRecords.show(false)

    val sriopen = afterUpdatedRecords.union(noChangeRecords).union(newRecords)
    sriopen.show(false)

  }

}
