package com.dunghn.tranningkotlin.ui.base

import androidx.lifecycle.ViewModel
import com.dunghn.tranningkotlin.data.network.UserApi
import com.dunghn.tranningkotlin.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(private val repository: BaseRepository) : ViewModel() {
    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) {
        repository.logout(api)
    }
}