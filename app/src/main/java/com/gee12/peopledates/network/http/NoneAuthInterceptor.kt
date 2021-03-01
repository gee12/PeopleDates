package com.gee12.peopledates.network.http

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor, используемый при отправке запроса на обновление OAuth токена авторизации.
 */
class NoneAuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        return chain.proceed(chain.request())
        val newRequestBuilder = chain.request().newBuilder()
//            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("Authorization",
                Credentials.basic("5DnfbqtVsw2HSVWTPx","B7w5ZHgqvAcUACmGjM35zQVPc9VJp82x")
            )
        return chain.proceed(newRequestBuilder.build())
    }
}