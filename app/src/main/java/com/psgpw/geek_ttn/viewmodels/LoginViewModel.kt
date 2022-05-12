package com.psgpw.geek_ttn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.psgpw.HireMe.data.ResponseData
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.data.models.request.LoginRequest
import com.psgpw.pickapp.data.models.BaseRequest
import com.psgpw.pickapp.data.models.User
import com.psgpw.pickapp.data.repos.NetworkRepo
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    private val networkRepo = NetworkRepo()

    lateinit var loginState: LiveData<ResultState<User?>>

   /* fun signInRegisterUser(baseRequest: BaseRequest) {
        viewModelScope.launch {
            loginState = networkRepo.getSignInUser(baseRequest).asLiveData()

        }
    }
*/
    fun signIn(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginState = networkRepo.login(loginRequest).asLiveData()

        }
    }

}