package com.gee12.peopledates.data

import com.google.gson.annotations.Expose

data class AccessToken(
        @Expose
        var token: String,

        @Expose
        var refreshToken: String
)