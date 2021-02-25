package com.gee12.peopledates.di

import android.content.Context
import com.gee12.peopledates.SharedPrefApi
import com.gee12.peopledates.data.LoginRepository
import com.gee12.peopledates.data.UserLocalDataSource
import com.gee12.peopledates.network.AccessTokenWrapper
import com.gee12.peopledates.network.LoginDataSource
//import com.gee12.peopledates.network.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSharedPrefApi(context: Context, gson: Gson): SharedPrefApi {
        return SharedPrefApi(context, gson)
    }

    @Singleton
    @Provides
    fun provideAccessTokenWrapper(sharedPrefApi: SharedPrefApi) : AccessTokenWrapper {
        return AccessTokenWrapper(sharedPrefApi)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
//        localDataSource: UserLocalDataSource,
        remoteDataSource: LoginDataSource,
        accessTokenWrapper: AccessTokenWrapper
    ): LoginRepository {
        return LoginRepository(remoteDataSource, accessTokenWrapper)
    }
}