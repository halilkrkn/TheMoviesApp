package com.halilkrkn.themoviesapp.data.remote.api

import com.halilkrkn.themoviesapp.core.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TheMoviesInstance {

    val apiService: TheMoviesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMoviesApi::class.java)
    }
}