package com.gee12.peopledates.network.http

import com.gee12.peopledates.network.AccessTokenWrapper
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
                .apply {
                    accessTokenWrapper.getAccessToken()?.let {
                        addHeader("Authorization", "Bearer ${it.token}")
                    }
                }
//                .header("Accept", "application/x-www-form-urlencoded")
//                .header("Content-Type", "application/x-www-form-urlencoded")
        return chain.proceed(authorisedRequestBuilder.build())
//        return chain.proceed(chain.request())
    }
}