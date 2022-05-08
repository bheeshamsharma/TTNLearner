package com.psgpw.pickapp.data.network

import com.psgpw.HireMe.data.ResponseData
import com.psgpw.HireMe.data.models.UserDetail
import com.psgpw.pickapp.data.models.LoginRequest
import com.psgpw.pickapp.data.models.User
import okhttp3.MultipartBody

import okhttp3.RequestBody

import retrofit2.http.*


interface ApiService {

    @POST("login")
    suspend fun signIn(@QueryMap queries: Map<String, String?>): ResponseData<User>

    @POST("login/")
    suspend fun registerUser(@Body loginRequest: LoginRequest): ResponseData<User>

    @POST("ForgetpasswordApi")
    suspend fun forgetPassword(@Query("email") email : String): ResponseData<Nothing>

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
    suspend fun userDetail(@Query("id") id:String): ResponseData<UserDetail>
}