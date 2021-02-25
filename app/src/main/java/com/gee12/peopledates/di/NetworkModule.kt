package com.gee12.peopledates.di

import android.content.Context
import coil.ImageLoader
import com.gee12.peopledates.BuildConfig
import com.gee12.peopledates.network.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideAuthApi(gson: Gson,
                           authInterceptor: AuthInterceptor,
                           loggingInterceptor: HttpLoggingInterceptor,
                           tokenAuthenticator: TokenAuthenticator       // использует INoneAuthApi
    ): IPersonsApi {
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(BuildConfig.BASE_URL, IPersonsApi::class.java, gson, tokenAuthenticator, interceptors)
    }

    @Singleton
    @Provides
    fun provideNoneAuthApi(gson: Gson,
                               noneAuthInterceptor: NoneAuthInterceptor,
                               loggingInterceptor: HttpLoggingInterceptor
    ): INoneAuthApi {
        val interceptors = arrayOf(noneAuthInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(BuildConfig.BASE_AUTH_URL, INoneAuthApi::class.java, gson, null, interceptors)
    }

    // TODO: вынести httpClient для сохранения
    @Singleton
    @Provides
    fun buildImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(ApiFactory.httpClient)
            .build()
    }
}