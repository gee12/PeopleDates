package com.gee12.peopledates.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor, используемый для вставки в заголовок запроса OAuth токена авторизации.
 */
class AuthInterceptor @Inject constructor(private val accessTokenWrapper: AccessTokenWrapper): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authorisedRequestBuilder = originalRequest.newBuilder()
                //.addQueryParameter("access_token", accessTokenWrapper.getAccessToken()!!.token)
                .addHeader("Authorization", /*"Bearer " +*/ accessTokenWrapper.getAccessToken()!!.token)
                .header("Accept", "application/json")
        return chain.proceed(authorisedRequestBuilder.build())
    }
}