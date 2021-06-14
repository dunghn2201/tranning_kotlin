package com.dunghn.tranningkotlin.data.network


import com.dunghn.tranningkotlin.data.response.LoginResponse
import com.dunghn.tranningkotlin.data.response.User
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Body user: User

    ): LoginResponse
}