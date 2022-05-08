package com.psgpw.pickapp.data.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.psgpw.HireMe.data.ResponseData
import com.psgpw.pickapp.data.models.*


object ApiHelper {

    // companion object {


    suspend fun getSignInUser(baseRequest: BaseRequest): ResponseData<User> {
        return RetrofitFactory.apiService.signIn(objectToHashMap(baseRequest))
    }

    suspend fun registerUser(baseRequest: String): ResponseData<User> {
        val hashMap: HashMap<String, String> = HashMap()
        hashMap.put("provider", "google")
        hashMap.put("access_token", baseRequest)
        return RetrofitFactory.apiService.registerUser(LoginRequest("google", baseRequest))
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