package com.gee12.peopledates.network

import com.gee12.peopledates.SharedPrefApi
import com.gee12.peopledates.data.AccessToken

class AccessTokenWrapper(private val sharedPrefApi: SharedPrefApi) {
    private var accessToken: AccessToken? = null

    fun getAccessToken(): AccessToken? {
        if (accessToken == null) {
            accessToken = sharedPrefApi.getObject(SharedPrefApi.ACCESS_TOKEN, AccessToken::class.java)
        }
        return accessToken
    }

    fun saveAccessToken(accessToken: AccessToken?) {
        this.accessToken = accessToken
        sharedPrefApi.putObject(SharedPrefApi.ACCESS_TOKEN, accessToken)
    }
}