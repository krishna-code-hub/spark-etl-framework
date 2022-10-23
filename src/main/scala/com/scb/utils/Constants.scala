package com.scb.utils

object Constants {

  //TODO It should be changed to actual column names that are currently used. If possible convert this to enum
  val DELETE_COLUMN_NAME = "rec_del_flag"
  val END_DATE_COLUMN_NAME = "endDate"
  val START_DATE_COLUMN_NAME = "stdate"
  val std_column = Seq((START_DATE_COLUMN_NAME),(END_DATE_COLUMN_NAME),(DELETE_COLUMN_NAME))

}
