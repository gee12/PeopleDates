package com.gee12.peopledates.network

import com.gee12.peopledates.data.AccessToken
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Используется, когда необходимо обновление OAuth токена,
 *  после того, как запрос вернет код ошибки 401.
 */
interface INoneAuthApi {

    //  TODO: https://bitbucket.org/site/oauth2/access_token

    @POST("/site/oauth2/access_token")
    fun login(@Body request: LoginRequest): Single<AccessToken>

    @POST("refresh_token")
    @FormUrlEncoded
    fun refreshToken(@Field("refresh_token") refreshToken: String): Single<AccessToken>

}