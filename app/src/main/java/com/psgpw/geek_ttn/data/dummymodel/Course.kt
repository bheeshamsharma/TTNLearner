package com.psgpw.geek_ttn.data.dummymodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Course(val id : String,val course_id : String ,var course_name:String, var description:String,var rating : Int,var enroll : Boolean)

data class CourseRequest(val user_id : String, var course_name:String, var description:String,var expert_email : String)

data class Topic(val id : String, var topic_name:String, var description:String,var assignment : List<Assignment>)

data class SubTopic(val id : String, var stopic_name:String, var description:String,var reflink : List<SubTopicRefLink>, val refvideos  : List<SubTopicRefVideo>)

@Parcelize
data class Assignment(val id : String, var assignment_id : String ,var user_id : String ,var topic_id : String, var assignment_name:String, var description:String, var assign_link  : String, var assignment_state : String,var align_expert :  String) : Parcelable

data class SubTopicRefLink(val id : String, var name:String)

data class SubTopicRefVideo(val id : String, var name:String)
