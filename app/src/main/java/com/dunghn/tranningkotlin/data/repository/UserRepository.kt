package com.dunghn.tranningkotlin.data.repository

import com.dunghn.tranningkotlin.data.network.UserApi


class UserRepository(
    private val api: UserApi,
) : BaseRepository() {
    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}