package com.psgpw.pickapp.data.network

import com.psgpw.HireMe.data.ResponseData
import com.psgpw.HireMe.data.models.UserDetail
import com.psgpw.geek_ttn.data.dummymodel.*
import com.psgpw.geek_ttn.data.models.request.LoginRequest
import com.psgpw.pickapp.data.models.User
import okhttp3.MultipartBody

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.*


interface ApiService {

    @POST("login")
    suspend fun signIn(@QueryMap queries: Map<String, String?>): ResponseData<User>

    @POST("login/")
    suspend fun registerUser(@Body loginRequest: LoginRequest): Response<User>

    @GET("courses")
    suspend fun getCourses(): Response<List<Course>>

    @GET("enrollcourse")
    suspend fun getEnrolledCourses(@Query("q") id: String): Response<List<Course>>

    @GET("submitassignment")
    suspend fun getAssignmentList(@Query("l") id: String): Response<List<Assignment>>

    @GET("coursestopic")
    suspend fun getTopics(@Query("q") id: String): Response<List<Topic>>

    @GET("subtopic")
    suspend fun getSubTopics(@Query("q") id: String): Response<List<SubTopic>>

    @GET("subTopicDetail/")
    suspend fun getSubTopicDetail(@Query("q") id: String): Response<SubTopic>

    @POST("enrollcourse")
    suspend fun enrollCourse(@Body hashMap: HashMap<String, String>): Response<Course>

    @POST("raisequeries")
    suspend fun newCourseRequest(@Body courseRequest: CourseRequest): Response<Course>

    @POST("submitassignment")
    suspend fun submitAssignment(@Body assignment: Assignment): Response<Assignment>

}