package com.gee12.peopledates.data

import com.gee12.peopledates.network.AccessTokenWrapper
import com.gee12.peopledates.network.LoginDataSource
import io.reactivex.Single
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

//class LoginRepository @Inject constructor(val dataSource: LoginDataSource) {
class LoginRepository @Inject constructor(private val loginDataSource: LoginDataSource,
                                          private val accessTokenWrapper: AccessTokenWrapper
) {

    // in-memory cache of the loggedInUser object
//    var user: LoggedInUser? = null
//        private set

//    val isLoggedIn: Boolean
//        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
//        user = null
    }

    fun logout() {
//        user = null
        loginDataSource.logout()
        accessTokenWrapper.saveAccessToken(null)
    }

    fun login(username: String, password: String): Result<Any> {
        // handle login
//        val result = dataSource.login(username, password)
        val result = loginDataSource.login(username, password)

        if (result is Result.Success) {
             result.data.flatMap {
                setLoggedInUser(it)
                Single.just(it)
            }
        }

        return result
    }

    private fun setLoggedInUser(accessToken: AccessToken) {
//        this.user = loggedInUser
        accessTokenWrapper.saveAccessToken(accessToken)
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}