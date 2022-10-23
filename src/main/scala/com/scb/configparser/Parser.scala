package com.scb.configparser

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import com.scb.models._

import java.io.File

object Parser {

  def schemaParse(configPath: String): schemaConfig = {
    val mapper = new ObjectMapper(new YAMLFactory)
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(new File(configPath), classOf[schemaConfig])
  }

}
