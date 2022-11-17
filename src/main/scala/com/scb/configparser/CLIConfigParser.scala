
package com.scb.configparser

import com.scb.utils.Utilities

import scala.collection.mutable.Map
import org.rogach.scallop._

import scala.collection.mutable.Map
import org.rogach.scallop.exceptions.Help
import org.rogach.scallop.exceptions.Exit
import org.rogach.scallop.exceptions.ScallopException
import org.rogach.scallop.exceptions.RequiredOptionNotFound

class CLIConfigParser(arguments: Seq[String]) extends ScallopConf(arguments) {

  banner("""Usage: Job Configuration [OPTIONS] ...
|For ETL job to work there are few configuration which needs to be passed.
|Options:
""")

  val processName = opt[String](
    name = "processName",
    required = true,
    descr = "Process Name is required")

  val country = opt[String](
    name = "country",
    required = true,
    descr = "Give the country name for which you are executing the job")

  val date = opt[String](
    name = "date",
    required = true,
    descr = "Date needs to be provided")

  val ctlId = opt[String](
    name = "ctlId",
    required = true,
    descr = "Give the ctlId")

 /* val runMode = opt[String](
    name = "runMode",
    default = Some("local"),
    descr = "Give the master for your job master/local")*/

  val groupId = opt[String](
    name = "groupId",
    required = true,
    descr = "Give the group id")

  val transformType = opt[String](
    name = "transformType",
    required = true,
    descr = "Give the transformType as core or extension")

  val source = opt[String](
    name = "source",
    required = true,
    descr = "Give the source name like uts,hog,ccm etc")



  def getArgMap(): Map[String, String] = {
    var argsMap = scala.collection.mutable.Map[String, String]()
    argsMap += ("processName" -> processName())
    argsMap += ("country" -> country())
    argsMap += ("date" -> date())
    argsMap += ("ctlId" -> ctlId())
    argsMap += ("groupId" -> groupId())
    argsMap += ("transformType" -> transformType())
    argsMap += ("source" -> source())
    argsMap
  }

  verify()

  override def onError(e: Throwable): Unit = e match {
    case Help("") => printHelp()
    case Exit() => printHelp()
    case ScallopException(message) => {
      println(message)
      printHelp()
    }
    case RequiredOptionNotFound(message) => {
      println(message)
      printHelp()
    }
    case other => throw other
  }

}



