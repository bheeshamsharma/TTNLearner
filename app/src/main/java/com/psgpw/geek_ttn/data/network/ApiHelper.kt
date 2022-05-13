package com.psgpw.pickapp.data.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.psgpw.HireMe.data.ResponseData
import com.psgpw.geek_ttn.data.dummymodel.*
import com.psgpw.geek_ttn.data.models.request.LoginRequest
import com.psgpw.pickapp.data.models.*
import retrofit2.Call
import retrofit2.Response
import java.lang.AssertionError


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

    suspend fun getEnrolledCourses(userId: String): Response<List<Course>> {
        return RetrofitFactory.apiService.getEnrolledCourses(userId)
    }

    suspend fun getAssignmentList(userId: String): Response<List<Assignment>> {
        return RetrofitFactory.apiService.getAssignmentList(userId)
    }

    suspend fun getTopicList(courseId: String): Response<List<Topic>> {
        return RetrofitFactory.apiService.getTopics(courseId)
    }

    suspend fun getSubTopicList(topicId: String): Response<List<SubTopic>> {
        return RetrofitFactory.apiService.getSubTopics(topicId)
    }

    suspend fun getSubTopicDetail(subTopicId: String): Response<SubTopic> {
        return RetrofitFactory.apiService.getSubTopicDetail(subTopicId)
    }

    suspend fun enrollCourseRequest(userId: String, CourseId: String): Response<Course> {
        val hashMap = HashMap<String, String>()
        hashMap.put("user_id", userId)
        hashMap.put("course_id", CourseId)
        return RetrofitFactory.apiService.enrollCourse(hashMap)
    }

    suspend fun newCourseRequest(courseRequest: CourseRequest): Response<Course> {
        return RetrofitFactory.apiService.newCourseRequest(courseRequest)
    }

    suspend fun submitAssignment(assignment: Assignment): Response<Assignment> {
        return RetrofitFactory.apiService.submitAssignment(assignment)
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