package com.psgpw.geek_ttn.data.dummymodel

data class Course(val id : String, var course_name:String, var description:String,var rating : Int)

data class Topic(val id : String, var topic_name:String, var description:String,var assignment : List<Assignment>)

data class SubTopic(val id : String, var stopic_name:String, var description:String,var reflink : List<SubTopicRefLink>, val refvideos  : List<SubTopicRefVideo>)

data class Assignment(val id : String, var name:String)

data class SubTopicRefLink(val id : String, var name:String)

data class SubTopicRefVideo(val id : String, var name:String)
