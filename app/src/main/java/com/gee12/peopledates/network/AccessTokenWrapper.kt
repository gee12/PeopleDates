package com.gee12.peopledates.network

import com.gee12.peopledates.data.SharedPrefApi
import com.gee12.peopledates.network.data.AccessToken

class AccessTokenWrapper(private val sharedPrefApi: SharedPrefApi) {
    private var accessToken: AccessToken? = null

    fun getAccessToken(): AccessToken? {
        if (accessToken == null) {
            accessToken = sharedPrefApi.getObject(SharedPrefApi.ACCESS_TOKEN, AccessToken::class.java)
//            accessToken = AccessToken(
//                "yQbVNSbf_KawpQo_IqvAq6CK4sazt6f44XGv_ZfR1y3ta0Sa1xhvtaak84FyNiihSDpuRi5cS2Spc6RIHisZ3Uq-OD44py4KB4lrZGiGzDwHrq0nov1AT8hb",
//                "")
        }
        return accessToken
    }

    fun saveAccessToken(accessToken: AccessToken?) {
        this.accessToken = accessToken
        sharedPrefApi.putObject(SharedPrefApi.ACCESS_TOKEN, accessToken)
    }
}