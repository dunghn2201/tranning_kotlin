package com.dunghn.tranningkotlin.data.response


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("result")
    val result: User,
    @SerializedName("success")
    val success: Int
)