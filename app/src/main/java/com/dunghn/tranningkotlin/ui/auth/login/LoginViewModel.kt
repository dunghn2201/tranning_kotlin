package com.dunghn.tranningkotlin.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dunghn.tranningkotlin.data.network.Resource
import com.dunghn.tranningkotlin.data.repository.AuthRepository
import com.dunghn.tranningkotlin.data.response.LoginResponse
import com.dunghn.tranningkotlin.ui.base.BaseViewModel
import com.dunghn.tranningkotlin.util.Event
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: AuthRepository,
) : BaseViewModel(repository) {
    var inputUserName = MutableLiveData<String>()
    var inputPassword = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun onLogin() {
        when {
            inputUserName.value == null -> {
                statusMessage.value = Event("Please enter username")
            }
            inputPassword == null -> {
                statusMessage.value = Event("Please enter password")
            }
            else -> {
                viewModelScope.launch {
                    _loginResponse.value = Resource.Loading
                    _loginResponse.value =
                        repository.login(inputUserName.value!!, inputPassword.value!!)
                }
            }
        }
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }

}