package com.scb.configparser

/* Contains the source structure of the yml config file */

case class SourceConfigParser(
                   sourceTableName: String,
                   var sourceSchemaName: Option[String],
                   var columnDelimiter: Option[String]
                 ){
  sourceSchemaName = Option(sourceSchemaName.getOrElse(""))
  columnDelimiter = Option(columnDelimiter.getOrElse(""))
}
