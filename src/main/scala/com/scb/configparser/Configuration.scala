package com.scb.configparser

case class Configuration(
                          var processName:Option[String],
                          var schemaName:Option[String]
                        ) {
  require(processName.isDefined, "Process Name Should be defined.")
  require(schemaName.isDefined, "Schema Name Should be defined")
}