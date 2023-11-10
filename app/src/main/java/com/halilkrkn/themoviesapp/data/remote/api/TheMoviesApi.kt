package com.halilkrkn.themoviesapp.data.remote.api

import com.halilkrkn.themoviesapp.core.Constants.API_KEY
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesPageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMoviesApi {
    @GET("discover/movie")
     suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("total_pages") totalPages: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): TheMoviesPageDto

    @GET("search/movie")
    suspend fun searchTheMovies(
        @Query("page") page: Int,
        @Query("total_pages") totalPages: Int,
        @Query("query") query: String,
//        @Query("year") year: String,
        @Query("api_key") apiKey: String = API_KEY
    ): TheMoviesPageDto
}