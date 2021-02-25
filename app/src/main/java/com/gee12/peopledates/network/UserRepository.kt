package com.gee12.peopledates.network

import com.gee12.peopledates.data.AccessToken
import com.gee12.peopledates.data.UserLocalDataSource
import io.reactivex.Single

/*
class UserRepository constructor(private val localDataSource: UserLocalDataSource,
                                 private val remoteDataSource: LoginDataSource,
                                 private val accessTokenWrapper: AccessTokenWrapper
) {

    fun login(username: String, password: String): Single<AccessToken> {
        return remoteDataSource.login(username, password).flatMap {
            accessTokenWrapper.saveAccessToken(it)
            Single.just(it)
        }
    }
}*/
