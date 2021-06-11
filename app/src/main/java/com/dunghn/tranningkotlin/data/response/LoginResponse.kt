package com.dunghn.tranningkotlin.data.response

data class LoginResponse(
    val message: String,
    val success: Int,
    val token: String,
    val user: User
)