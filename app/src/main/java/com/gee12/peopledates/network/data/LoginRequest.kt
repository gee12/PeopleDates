package com.gee12.peopledates.network.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class LoginRequest(

        @SerializedName("username")
        @Expose
        val userName: String,

        @SerializedName("password")
        @Expose
        val password: String,

        @SerializedName("grant_type")
        @Expose
        val grant_type: String = "password"
)
