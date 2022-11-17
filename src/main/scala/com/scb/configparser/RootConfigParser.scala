package com.scb.configparser

/* Contains the root structure of the yml config file */

case class RootConfigParser(
                 paramFile: String,
                 config: List[ProcessConfigParser]
               )




