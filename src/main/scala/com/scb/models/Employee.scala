package com.scb.models

import java.sql.Date

case class Employee(id:Integer,
                    name:String,
                    dept:String,
                    country:String,
                    occupation:String,stdate:String ,
                    endDate: String,
                    rec_del_flag: String)

case class schemaConfig(tableSchema : List[tableSchema] ) extends Serializable

case class tableSchema(tablename : String,
                       primary_key: String ,
                       description: Option[String] ,
                       columns: List[columnStructure] ) extends Serializable

case class columnStructure( name: String,
                            datatype: String,
                            description: Option[String],
                            not_null: String) extends Serializable

case class AppConfig(
                      sparkAppConfig: SparkAppConfig,
                      streamingQueryConfig: StreamingQueryConfig
                    ) extends Serializable

case class SparkAppConfig(
                           appName: String,
                           core: Map[String, String]
                         ) extends Serializable

case class StreamingQueryConfig(
                                 streamName: String,
                                 triggerInterval: String,
                                 triggerEnabled: Boolean,
                                 windowInterval: String,
                                 watermarkInterval: String
                               ) extends Serializable
