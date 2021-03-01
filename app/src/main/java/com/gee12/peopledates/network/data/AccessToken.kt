package com.gee12.peopledates.network.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccessToken(

//        @SerializedName("scopes")
//        @Expose
//        val scopes: String,

        @SerializedName("access_token")
        @Expose
        val token: String,

//        @SerializedName("expires_in")
//        @Expose
//        val expiresIn: Int,

//        @SerializedName("token_type")
//        @Expose
//        val tokenType: String,

//        @SerializedName("state")
//        @Expose
//        val state: String,

        @SerializedName("refresh_token")
        @Expose
        val refreshToken: String
)