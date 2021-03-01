package com.gee12.peopledates.network

import com.gee12.peopledates.network.data.AccessToken
import com.gee12.peopledates.network.api.OAuthApi
import java.io.IOException
import javax.inject.Inject


class LoginDataSource @Inject constructor(private val noneAuthApi: OAuthApi) {

    suspend fun login(username: String, password: String): Result<AccessToken> {
        return try {
            noneAuthApi.login(username, password, "password")
//            noneAuthApi.login(LoginRequest(username, password))
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}