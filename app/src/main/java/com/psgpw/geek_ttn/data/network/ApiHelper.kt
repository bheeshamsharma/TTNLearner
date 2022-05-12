package com.psgpw.pickapp.data.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.psgpw.HireMe.data.ResponseData
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.data.dummymodel.SubTopic
import com.psgpw.geek_ttn.data.dummymodel.Topic
import com.psgpw.geek_ttn.data.models.request.LoginRequest
import com.psgpw.pickapp.data.models.*
import retrofit2.Call
import retrofit2.Response


object ApiHelper {

    // companion object {


    suspend fun getSignInUser(baseRequest: BaseRequest): ResponseData<User> {
        return RetrofitFactory.apiService.signIn(objectToHashMap(baseRequest))
    }

    suspend fun registerUser(loginRequest: LoginRequest): Response<User> {
        return RetrofitFactory.apiServiceLogin.registerUser(loginRequest)
    }

    suspend fun getCourses(): Response<List<Course>> {
        return RetrofitFactory.apiService.getCourses()
    }

    suspend fun getTopicList(courseId : String): Response<List<Topic>> {
        return RetrofitFactory.apiService.getTopics(courseId)
    }

    suspend fun getSubTopicList(topicId : String): Response<List<SubTopic>> {
        return RetrofitFactory.apiService.getSubTopics(topicId)
    }

    suspend fun getSubTopicDetail(subTopicId : String): Response<SubTopic> {
        return RetrofitFactory.apiService.getSubTopicDetail(subTopicId)
    }
//
//    suspend fun forgotPassword(baseRequest: BaseRequest) {
//        RetrofitFactory.apiService.forgetPassword(objectToHashMap(baseRequest))
//    }
//
//    suspend fun editUser(baseRequest: BaseRequest): ResponseData<Nothing> {
//        return RetrofitFactory.apiService.userEditProfile(objectToHashMap(baseRequest))
//    }

//    suspend fun editUserImage(uri: ByteArray, id: String): ResponseData<User> {
//        // val file = File(uri.path)
//        val requestFile: RequestBody = RequestBody.create(
//            MediaType.parse("multipart/form-data"), uri
//        )
//
//        val body = MultipartBody.Part.createFormData("userImage", "profile_image", requestFile)
//
//        val id = RequestBody.create(MultipartBody.FORM, id)
//
//        return RetrofitFactory.apiService.editUserImage(id, body)
//    }


    private fun objectToHashMap(baseRequest: BaseRequest): Map<String, String?> {
        val gson: Gson = Gson()
        return gson.fromJson<Map<String, String?>>(
            gson.toJson(baseRequest),
            object : TypeToken<Map<String, String?>>() {}.type
        )
    }
}