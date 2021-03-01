package com.gee12.peopledates.network.api

import com.gee12.peopledates.network.data.AccessToken
import com.gee12.peopledates.network.Result
import retrofit2.http.*

/**
 * Используется, когда необходимо обновление OAuth токена,
 *  после того, как запрос вернет код ошибки 401.
 */
interface OAuthApi {

//    @Headers("Content-Type: application/x-www-form-urlencoded")
//    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("/site/oauth2/access_token")
    suspend fun login(
//        @Body request: LoginRequest
        @Field("username")userName: String,
        @Field("password")password: String,
        @Field("grant_type")type: String
    ): Result<AccessToken>

    @POST("refresh_token")
    @FormUrlEncoded
    suspend fun refreshToken(
//        @Body request: RefreshTokenRequest
        @Field("refresh_token")refreshToken: String,
        @Field("grant_type")grantType: String,
    ): Result<AccessToken>

}