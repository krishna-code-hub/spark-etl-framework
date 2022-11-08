package com.scb.configparser

case class ProcessConfiguration(
  var processName : Option[String],
  var ctlID : Option[String],
  var groupID : Option[String],
  var countryCode : Option[String],
  var sourceSystemName : Option[String],
  var tQuery : Option[String],
  var Source : Option[Source],
  var processType : Option[String],
  var rejectionThreshold : Option[String],
  var runOrder : Option[String],
  var source : Option[String],
  var target : Option[String],
  var validationRules: Array[ValidationConfiguration]
) {

  //require(filterCondition.isDefined, "Filter name should be defined to churn data based on the condition.")
}
