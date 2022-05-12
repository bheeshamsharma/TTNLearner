package com.psgpw.pickapp.data.network

import com.psgpw.HireMe.data.ResponseData
import com.psgpw.HireMe.data.models.UserDetail
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.data.dummymodel.SubTopic
import com.psgpw.geek_ttn.data.dummymodel.Topic
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

    @GET("coursestopic")
    suspend fun getTopics(@Query("q") id: String): Response<List<Topic>>

    @GET("subtopic")
    suspend fun getSubTopics(@Query("q") id: String): Response<List<SubTopic>>

    @GET("subTopicDetail/")
    suspend fun getSubTopicDetail(@Query("q") id: String): Response<SubTopic>

    @POST("ForgetpasswordApi")
    suspend fun forgetPassword(@Query("email") email: String): ResponseData<Nothing>

    @POST("UpdatepasswordApi")
    suspend fun updatePassword(@QueryMap queries: Map<String, String?>): ResponseData<Nothing>

    @Multipart
    @POST("UpdateProfileImage")
    suspend fun editUserImage(
        @Part("id") key: RequestBody,
        @Part file: MultipartBody.Part?
    ): ResponseData<Nothing>

    @Multipart
    @POST("UpdateProfileVideo")
    suspend fun updateProfileVideo(
        @Part("id") id: RequestBody,
        @Part file: MultipartBody.Part?
    ): ResponseData<Nothing>

    @POST("UserEditprofileApi")
    suspend fun userEditProfile(@QueryMap queries: Map<String, String?>): ResponseData<User>

    @POST("UserDetailApi")
    suspend fun userDetail(@Query("id") id: String): ResponseData<UserDetail>
}