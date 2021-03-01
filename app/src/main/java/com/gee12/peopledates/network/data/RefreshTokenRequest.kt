package com.gee12.peopledates.network.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(

        @SerializedName("refresh_token")
        @Expose
        var refreshToken: String,

        @SerializedName("grant_type")
        @Expose
        var grantType: String = "refresh_token"
)
