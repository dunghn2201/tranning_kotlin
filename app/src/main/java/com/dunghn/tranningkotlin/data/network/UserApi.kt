package com.dunghn.tranningkotlin.data.network

import com.dunghn.tranningkotlin.data.response.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("auth")
    suspend fun getUser():UserResponse

    @POST("logout")
    suspend fun logout():ResponseBody
}