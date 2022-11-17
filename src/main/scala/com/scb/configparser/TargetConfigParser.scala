package com.scb.configparser

/* Contains the target table structure of the yml config file */

case class TargetConfigParser(
                 var targetTableName: String,
                 var targetTableSchema: Option[String],
                 var targetKeyColumn: Option[String]
                 ) {
  targetTableSchema = Option(targetTableSchema.getOrElse(""))
  targetKeyColumn = Option(targetKeyColumn.getOrElse(""))
}
