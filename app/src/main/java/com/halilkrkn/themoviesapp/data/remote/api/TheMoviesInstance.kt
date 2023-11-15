package com.halilkrkn.themoviesapp.data.remote.api

import com.halilkrkn.themoviesapp.core.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TheMoviesInstance {

    val apiService: TheMoviesApi by lazy {
        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                chain.proceed(requestBuilder.build())
            })
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMoviesApi::class.java)
    }
}