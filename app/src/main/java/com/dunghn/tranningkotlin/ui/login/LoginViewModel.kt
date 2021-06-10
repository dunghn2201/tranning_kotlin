package com.dunghn.tranningkotlin.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dunghn.tranningkotlin.util.Event

class LoginViewModel : ViewModel() {
    var inputEmail = MutableLiveData<String>()
    var inputPassword = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    fun onLogin() {
        if (inputEmail.value == null) {
            statusMessage.value = Event("Please enter email")
        } else if (inputPassword == null) {
            statusMessage.value = Event("Please enter password")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value).matches()) {
            statusMessage.value = Event("Please enter valid email")
        } else {
            statusMessage.value = Event("Login Successfully")
        }
    }

}