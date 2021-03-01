package com.gee12.peopledates.network.http

import com.gee12.peopledates.network.MyCallAdapterFactory
import com.google.gson.Gson
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object {

        private const val CONNECTION_TIMEOUT = 15L

        fun <T> generate(
            baseUrl: String,
            serviceClass: Class<T>,
            gson: Gson,
            okHttpClient: OkHttpClient
        ): T {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
//                .apply {
//                    if (serviceClass == OAuthApi::class.java) {
//                        addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(gson))
                .addCallAdapterFactory(MyCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
//                    }
//                }
                .build()
                .create(serviceClass)
        }


        fun createHttpClient(
            authenticator: Authenticator?,
            interceptors: Array<Interceptor>
        ): OkHttpClient {

            val okHttpClientBuilder = OkHttpClient().newBuilder()

            for (interceptor in interceptors) {
                okHttpClientBuilder.addInterceptor(interceptor)
            }
            okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            authenticator?.let {
                okHttpClientBuilder.authenticator(it)
            }
            return okHttpClientBuilder.build()
        }
    }
}
