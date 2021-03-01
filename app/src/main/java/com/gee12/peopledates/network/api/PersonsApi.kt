package com.gee12.peopledates.network.api

import com.gee12.peopledates.network.Result
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Используется для отправки запросов на сервер с использованием OAuth токена.
 */
interface PersonsApi {

    /**
     *
     * @param path Путь к каталогу записи в репозитории.
     *  Пример: "user_name/repo_name/src/branch_name/base/dir_name"
     */
    @GET("repositories/{pathToFolder}text.html")
    suspend fun loadFileContent(
            @Path("pathToFolder", encoded = true) path : String
    ) : Result<String>
}