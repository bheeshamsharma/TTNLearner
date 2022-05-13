package com.psgpw.pickapp.data.repos

import android.app.Application
import android.net.Uri
import com.psgpw.HireMe.data.ResponseData
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.data.dummymodel.*
import com.psgpw.geek_ttn.data.models.request.LoginRequest
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
        loginRequest: LoginRequest
    ): Flow<ResultState<User?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.registerUser(loginRequest)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getEnrolledCourse(userId: String
    ): Flow<ResultState<List<Course>?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.getEnrolledCourses(userId)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAssignmentList(userId: String
    ): Flow<ResultState<List<Assignment>?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.getAssignmentList(userId)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun getCourses(
    ): Flow<ResultState<List<Course>?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.getCourses()
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getTopics(
        courseId: String
    ): Flow<ResultState<List<Topic>?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.getTopicList(courseId)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getSubTopics(
        topicId: String
    ): Flow<ResultState<List<SubTopic>?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.getSubTopicList(topicId)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getSubTopicDetail(
        subTopicId: String
    ): Flow<ResultState<SubTopic?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.getSubTopicDetail(subTopicId)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun newCourseRequest(
        courseRequest: CourseRequest
    ): Flow<ResultState<Course?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.newCourseRequest(courseRequest)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun submitAssignment(
        assignment: Assignment
    ): Flow<ResultState<Assignment?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.submitAssignment(assignment)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun enrollCourseRequest(
        userId: String,CourseId: String
    ): Flow<ResultState<Course?>> = flow {
        try {
            emit(ResultState.Loading)
            val responseData = ApiHelper.enrollCourseRequest(userId,CourseId)
            if (responseData.isSuccessful) {
                responseData.body().let {
                    emit(ResultState.Success(it))
                }
            } else {
                emit(ResultState.Error(Exception(responseData.errorBody().toString())))
            }
        } catch (ex: Exception) {
            emit(ResultState.Error(ex))
        }
    }.flowOn(Dispatchers.IO)

}