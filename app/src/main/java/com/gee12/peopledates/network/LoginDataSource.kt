package com.gee12.peopledates.network

import com.gee12.peopledates.data.AccessToken
import com.gee12.peopledates.data.Result
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject

class LoginDataSource @Inject constructor(private val noneAuthApi: INoneAuthApi) {

    fun login(username: String, password: String): Result<Single<AccessToken>> {
        return try {
            val result = noneAuthApi.login(LoginRequest(username, password))
            Result.Success(result)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
//    fun login(username: String, password: String): Result<AccessToken> {
//        try {
//            // TODO: handle loggedInUser authentication
////            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
//            val res = noneAuthApi.login(LoginRequest(username, password)).blockingGet()
//            return Result.Success(res)
//        } catch (e: Throwable) {
//            return Result.Error(IOException("Error logging in", e))
//        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}