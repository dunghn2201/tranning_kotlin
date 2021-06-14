package com.dunghn.tranningkotlin.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Int,
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User
)