package com.dunghn.tranningkotlin.data.network

import com.dunghn.tranningkotlin.data.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("auth/login")
    suspend  fun login(@Field("username") email: String, @Field("password") password: String): LoginResponse
}