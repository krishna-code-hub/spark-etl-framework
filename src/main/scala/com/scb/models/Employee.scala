package com.scb.models

import java.sql.Date

case class Employee(id:Integer,
                    name:String,
                    dept:String,
                    country:String,
                    occupation:String,stdate:String ,
                    endDate: String,
                    rec_del_flag: String)
