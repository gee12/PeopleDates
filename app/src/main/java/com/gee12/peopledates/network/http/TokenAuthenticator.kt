package com.gee12.peopledates.network.http

import android.util.Log
import com.gee12.peopledates.network.AccessTokenWrapper
import com.gee12.peopledates.network.Result
import com.gee12.peopledates.network.api.OAuthApi
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException
import javax.inject.Inject

/**
 * Класс для отправки запроса на продление действия OAuth2 токена.
 */
class TokenAuthenticator @Inject constructor(
    private val noneAuthAPI: OAuthApi,
    private val accessTokenWrapper: AccessTokenWrapper
    ) : Authenticator {


    /**
     * Возвращает запрос, который включает учетные данные для удовлетворения запроса аутентификации в ответе.
     * Возвращает null, если задача не может быть выполнена.
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = accessTokenWrapper.getAccessToken() ?: return null

        val tokenResponse = runBlocking {
            // отправляем запрос на продление токена
            noneAuthAPI.refreshToken(
//                RefreshTokenRequest(accessToken.refreshToken)
                accessToken.refreshToken, "refresh_token"
            )
        }

        when (tokenResponse) {
            is Result.Success -> {
                // сохраняем новый токен локально
                val newAccessToken = tokenResponse.data!!
                accessTokenWrapper.saveAccessToken(newAccessToken)

                Log.i("TAG", "authenticator")
                // отправляем вновь невыполненный запрос, но уже с новым токеном
                return response.request.newBuilder()
                    .header("Authorization", "Bearer ${newAccessToken.token}")
//                .header("Content-Type", "application/x-www-form-urlencoded")
                    .build()
            }
            else -> {
                throw IOException("Error with access token refresh")
            }
        }
    }

/*    override fun authenticate(route: Route?, response: Response): Request {
        // отправляем запрос на продление токена
        val newAccessToken = noneAuthAPI.refreshToken(
            RefreshTokenRequest(accessTokenWrapper.getAccessToken()!!.refreshToken)
        ).blockingGet()
        // сохраняем новый токен локально
        accessTokenWrapper.saveAccessToken(newAccessToken)

        Log.i("TAG", "authenticator")
        // отправляем вновь невыполненный запрос, но уже с новым токеном
        return response.request.newBuilder()
                .header("Authorization", newAccessToken.token)
                .build()
    }*/
}