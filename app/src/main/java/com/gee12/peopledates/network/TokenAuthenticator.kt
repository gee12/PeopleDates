package com.gee12.peopledates.network

import android.util.Log
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

/**
 * Класс для отправки запроса на продление действия OAuth2 токена.
 */
class TokenAuthenticator @Inject constructor(
    private val noneAuthAPI: INoneAuthApi,
    private val accessTokenWrapper: AccessTokenWrapper
    ) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {

        // отправляем запрос на продление токена
        val newAccessToken = noneAuthAPI.refreshToken(accessTokenWrapper.getAccessToken()!!.refreshToken).blockingGet()
        // сохраняем новый токен локально
        accessTokenWrapper.saveAccessToken(newAccessToken)

        Log.i("TAG", "authenticator")
        // отправляем вновь невыполненный запрос, но уже с новым токеном
        return response.request.newBuilder()
                .header("Authorization", newAccessToken.token)
                .build()
    }
}