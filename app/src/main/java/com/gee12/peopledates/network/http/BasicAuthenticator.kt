package com.gee12.peopledates.network.http

import okhttp3.*
import javax.inject.Inject

/**
 * Класс для отправки запроса на авторизацию.
 */
class BasicAuthenticator @Inject constructor() : Authenticator {

    /**
     * TODO: Почему-то вообще не вызывается !
     *
     * Возвращает запрос, который включает учетные данные для удовлетворения запроса аутентификации в ответе.
     * Возвращает null, если задача не может быть выполнена.
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        // отправляем вновь невыполненный запрос, но уже с логином и паролем
        return response.request.newBuilder()
            .header("Authorization",
                Credentials.basic("5DnfbqtVsw2HSVWTPx","B7w5ZHgqvAcUACmGjM35zQVPc9VJp82x")
            )
            .build()
    }
}