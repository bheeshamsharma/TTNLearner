package com.psgpw.pickapp.data.repos

import android.app.Application
import android.net.Uri
import com.psgpw.HireMe.data.ResponseData
import com.psgpw.HireMe.data.ResultState
import com.psgpw.pickapp.data.models.BaseRequest
import com.psgpw.pickapp.data.models.User
import com.psgpw.pickapp.data.network.ApiHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.coroutines.Continuation

class NetworkRepo {

    suspend fun getSignInUser(
        baseRequest: BaseRequest
    ): Flow<ResultState<ResponseData<User>>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.getSignInUser(baseRequest)
            if (responseData.status) {
                emit(ResultState.Success(responseData))

            } else {
                emit(ResultState.Error(Exception(responseData.message)))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }

    }.flowOn(Dispatchers.IO)


    suspend fun login(
        baseRequest: String
    ): Flow<ResultState<ResponseData<User>>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.registerUser(baseRequest)
            if (responseData.status) {
                emit(ResultState.Success(responseData))
            } else {
                emit(ResultState.Error(Exception(responseData.message)))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)


//    suspend fun editUser(
//        baseRequest: BaseRequest
//    ): Flow<ResultState<ResponseData<Nothing>>> = flow {
//        try {
//            emit(ResultState.Loading)
//            val responseData = ApiHelper.editUser(baseRequest)
//            if (responseData.status) {
//                emit(ResultState.Success(responseData))
//            } else {
//                emit(ResultState.Error(Exception(responseData.message)))
//            }
//        } catch (ex: Exception) {
//            emit(ResultState.Error(ex))
//        }
//    }.flowOn(Dispatchers.IO)

//    suspend fun editUserImage(
//        apiKey: String, file: ByteArray
//    ): Flow<ResultState<ResponseData<User>>> = flow {
//        //try {
//            emit(ResultState.Loading)
//            val responseData = ApiHelper.editUserImage(id = apiKey, uri = file)
//            if (responseData.status) {
//                emit(ResultState.Success(responseData))
//            } else {
//                emit(ResultState.Error(Exception(responseData.message)))
//            }
//      //  } catch (ex: Exception) {
//        //    emit(ResultState.Error(ex))
//        //}
//    }.flowOn(Dispatchers.IO)
//        .catch { e ->
//            emit(ResultState.Error(Exception(e.message)))
//        }


}