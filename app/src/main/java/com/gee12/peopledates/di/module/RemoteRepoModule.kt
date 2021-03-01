package com.gee12.peopledates.di.module

import android.content.Context
import coil.ImageLoader
import com.gee12.peopledates.BuildConfig
import com.gee12.peopledates.network.api.OAuthApi
import com.gee12.peopledates.network.api.PersonsApi
import com.gee12.peopledates.network.http.AuthInterceptor
import com.gee12.peopledates.network.http.NoneAuthInterceptor
import com.gee12.peopledates.network.http.ServiceGenerator
import com.gee12.peopledates.network.http.TokenAuthenticator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Suppress("unused")
@Module
class RemoteRepoModule {

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    @Named("NoneAuthHttpClient")
    fun provideNoneAuthHttpClient(
        authInterceptor: NoneAuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
//        basicAuthenticator: BasicAuthenticator // не работает
    ): OkHttpClient {
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
//        return ServiceGenerator.createHttpClient(basicAuthenticator, interceptors)
        return ServiceGenerator.createHttpClient(null, interceptors)
    }

    @Singleton
    @Provides
    @Named("AuthHttpClient")
    fun provideAuthHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        tokenAuthenticator: TokenAuthenticator // использует INoneAuthApi
    ): OkHttpClient {
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
        return ServiceGenerator.createHttpClient(tokenAuthenticator, interceptors)
    }

    @Singleton
    @Provides
    fun provideNoneAuthApi(gson: Gson,
                           @Named("NoneAuthHttpClient") httpClient: OkHttpClient
    ): OAuthApi {
        return ServiceGenerator.generate(BuildConfig.BASE_AUTH_URL, OAuthApi::class.java, gson, httpClient)
    }

    @Singleton
    @Provides
    fun provideAuthApi(gson: Gson,
                       @Named("AuthHttpClient") httpClient: OkHttpClient
    ): PersonsApi {
        return ServiceGenerator.generate(BuildConfig.BASE_URL, PersonsApi::class.java, gson, httpClient)
    }

    @Singleton
    @Provides
    fun buildImageLoader(
        context: Context,
        @Named("AuthHttpClient") httpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(httpClient)
            .build()
    }
}