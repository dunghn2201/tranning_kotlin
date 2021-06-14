package com.dunghn.tranningkotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dunghn.tranningkotlin.data.network.Resource
import com.dunghn.tranningkotlin.data.repository.UserRepository
import com.dunghn.tranningkotlin.data.response.UserResponse
import com.dunghn.tranningkotlin.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : BaseViewModel(repository) {
    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val user: LiveData<Resource<UserResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}