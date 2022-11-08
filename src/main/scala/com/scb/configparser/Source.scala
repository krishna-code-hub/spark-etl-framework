package com.scb.configparser

case class Source(
                   var sourceTableName: Option[String],
                   var sourceSchemaName:Option[String],
                   var columnDelimiter:Option[String]
                 ){
  sourceSchemaName = Option(sourceSchemaName.getOrElse(""))
  columnDelimiter = Option(columnDelimiter.getOrElse(""))
}
