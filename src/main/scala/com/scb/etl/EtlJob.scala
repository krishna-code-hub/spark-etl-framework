package com.scb.etl

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.apache.log4j.LogManager
import com.scb.configparser.{CLIConfigParser, RootConfigParser}
import com.fasterxml.jackson.databind.ObjectMapper

import java.io.File


object EtlJob {

  /*
  * --processName UTS_HK_PROCESS --country hk --date 20210201 --ctlId 20050 --groupId 6 --transformType core --source uts
  * */

  def main(args: Array[String]): Unit = {

    //val log = LogManager.getLogger(this.getClass.getName)
    val conf = new CLIConfigParser(args)
    // Write the code to get the values from CLI
    val filename = "configurations/process-config.yml"

    val mapper = new ObjectMapper(new YAMLFactory())
    mapper.findAndRegisterModules();
    val yaml: RootConfigParser = mapper.readValue(new File(filename), classOf[RootConfigParser])
    val jsonString: String = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(yaml)
    val jsonObj = mapper.readTree(jsonString)

    println("YAML Config File: " + jsonObj.toPrettyString())

    // Creating spark session
    val sparkSession = Job.createSparkSession(jobName, runMode)

  }
}

