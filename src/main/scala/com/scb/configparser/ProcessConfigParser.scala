package com.scb.configparser

/* Contains the process part of the yml config file*/

case class ProcessConfigParser(
                                process_name: String,
                                ctl_id: String,
                                group_id: String,
                                country_code: String,
                                source_system_name: String,
                                tQuery: String,
                                process_type: String,
                                rejection_threshold: Double,
                                run_order: Double,
                                source: SourceConfigParser,
                                target: TargetConfigParser,
                                validationRules: Array[ValidationConfigParser]
                               ) {

  //require(filterCondition.isDefined, "Filter name should be defined to churn data based on the condition.")
}
