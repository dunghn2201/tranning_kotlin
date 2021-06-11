package com.dunghn.tranningkotlin.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dunghn.tranningkotlin.data.repository.AuthRepository
import com.dunghn.tranningkotlin.data.repository.BaseRepository
import com.dunghn.tranningkotlin.data.repository.UserRepository
import com.dunghn.tranningkotlin.ui.auth.login.LoginViewModel
import com.dunghn.tranningkotlin.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}