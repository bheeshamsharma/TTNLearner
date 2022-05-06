package com.psgpw.pickapp.data.network

import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import androidx.documentfile.provider.DocumentFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.psgpw.HireMe.data.ResponseData
import com.psgpw.pickapp.data.models.*
import okhttp3.MediaType
import okhttp3.RequestBody

import okhttp3.MultipartBody
import java.io.File


object ApiHelper {

    // companion object {


    suspend fun getSignInUser(baseRequest: BaseRequest): ResponseData<User> {
        return RetrofitFactory.apiService.signIn(objectToHashMap(baseRequest))
    }

    suspend fun registerUser(baseRequest: BaseRequest): ResponseData<User> {
        return RetrofitFactory.apiService.registerUser(objectToHashMap(baseRequest))
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