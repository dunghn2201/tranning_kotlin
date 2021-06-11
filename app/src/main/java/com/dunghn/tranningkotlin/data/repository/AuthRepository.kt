package com.dunghn.tranningkotlin.data.repository

import com.dunghn.tranningkotlin.data.network.AuthApi

class AuthRepository(private val api: AuthApi) : BaseRepository() {
    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(username, password)
    }

}