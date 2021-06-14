package com.dunghn.tranningkotlin.data.response

import com.google.gson.annotations.SerializedName

class User() {
    constructor (
        username: String,
        password: String
    ) : this()

    @SerializedName("account_balance")
    val accountBalance: Int? = 0

    @SerializedName("address")
    val address: String? = ""

    @SerializedName("avata")
    val avata: String? = ""

    @SerializedName("code")
    val code: String? = ""

    @SerializedName("created_at")
    val createdAt: String? = ""

    @SerializedName("email")
    val email: String? = ""

    @SerializedName("facebook")
    val facebook: String? = ""

    @SerializedName("fullname")
    val fullname: String? = ""

    @SerializedName("id")
    val id: Int? = 0

    @SerializedName("id_group")
    val idGroup: Int? = 0

    @SerializedName("name_group")
    val nameGroup: String? = ""

    @SerializedName("name_store")
    val nameStore: String? = ""

    @SerializedName("password")
    val password: String? = ""

    @SerializedName("phone")
    val phone: String? = ""

    @SerializedName("status")
    val status: Int? = 0

    @SerializedName("url_store")
    val urlStore: String? = ""

    @SerializedName("username")
    val username: String? = ""

    @SerializedName("zalo")
    val zalo: String? = ""

}