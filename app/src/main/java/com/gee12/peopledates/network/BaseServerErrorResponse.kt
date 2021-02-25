package com.gee12.peopledates.network

import com.google.gson.annotations.Expose

data class BaseServerErrorResponse(
    @Expose
    val success: Boolean,
    @Expose
    val error: Error
) {
    data class Error(
        @Expose
        val code: Int,
        @Expose
        val message: String
    )
}