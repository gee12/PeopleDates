package com.gee12.peopledates.network

import com.google.gson.annotations.Expose

data class LoginRequest(
        @Expose
        var userName: String,
        @Expose
        var password: String
)
