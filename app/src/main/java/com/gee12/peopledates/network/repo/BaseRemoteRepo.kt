package com.gee12.peopledates.network.repo

import android.util.Log
import com.gee12.peopledates.network.Result
import retrofit2.Response
import java.io.IOException

/**
 * From: https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15
 */
open class BaseRemoteRepo {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): T? {

        val result : Result<T> = safeApiResult(call, errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("BaseRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }
        return data
    }

    suspend fun <T: Any> safeApiResult(
        call: suspend ()-> Response<T>,
        errorMessage: String
    ) : Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}