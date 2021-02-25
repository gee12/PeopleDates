package com.gee12.peopledates.network

import android.content.Context
import coil.ImageLoader
import com.gee12.peopledates.BuildConfig
import com.gee12.peopledates.SharedPrefApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

//    private const val BASE_URL = "https://api.bitbucket.org/2.0/"
//    private const val API_KEY_HEADER = "x-api-key"
    private const val API_KEY_PARAM = "access_token"
    private const val API_KEY = "..."
    private const val CONNECTION_TIMEOUT = 15L

    private val authInterceptor = Interceptor { chain->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter(API_KEY_PARAM, API_KEY)
            .build()

        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val newRequest = originalRequest.newBuilder()
//            .url(originalHttpUrl)
            .url(newUrl)
//            .header(API_KEY_HEADER, "Bearer " + API_KEY)
            .build()

        chain.proceed(newRequest)
    }
//    private val json = Json {
//        ignoreUnknownKeys = true
//    }

//    private fun httpLoggingInterceptor() =
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val httpClient = OkHttpClient().newBuilder()
//        .addInterceptor(httpLoggingInterceptor())
        .addInterceptor(authInterceptor)
        .build()

//    val imageLoader = ImageLoader.Builder(context = ).
//        .okHttpClient(client)
//        .build()
//    var imageLoader: ImageLoader? = null

    // TODO: убрать в NetworkModule
    fun buildImageLoader(context: Context): ImageLoader = ImageLoader.Builder(context)
        .okHttpClient(httpClient)
        .build()

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
//        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val personsApi: IPersonsApi = retrofit.create(IPersonsApi::class.java)
//    val nonAuthApi: INoneAuthApi = retrofit.create(INoneAuthApi::class.java)

//    private val gson = GsonBuilder()
//        .excludeFieldsWithoutExposeAnnotation()
//        .create()

//    private val sharedPrefApi = SharedPrefApi()
//
//    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//
//    val nonAuthApi: INoneAuthApi = generate(BASE_URL, INoneAuthApi::class.java, gson,
//        null, arrayOf(authInterceptor, loggingInterceptor))
//
//    private val tokenAuthenticator = TokenAuthenticator(nonAuthApi, AccessTokenWrapper())
//
//    val personsApi: IPersonsApi = generate(BASE_URL, IPersonsApi::class.java, gson,
//        tokenAuthenticator, arrayOf(authInterceptor, loggingInterceptor))

}