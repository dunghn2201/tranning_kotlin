package com.dunghn.tranningkotlin.data.repository

import com.dunghn.tranningkotlin.data.UserPreferences
import com.dunghn.tranningkotlin.data.network.AuthApi
import com.dunghn.tranningkotlin.data.response.User



class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {
    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(User(username, password))
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }
}