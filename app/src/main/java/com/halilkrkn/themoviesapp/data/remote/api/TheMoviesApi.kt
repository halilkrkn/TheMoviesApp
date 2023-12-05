package com.halilkrkn.themoviesapp.data.remote.api

import com.halilkrkn.themoviesapp.core.Constants.API_KEY
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesAllDto
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieListsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMoviesApi {
    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("total_pages") totalPages: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): TheMoviesAllDto

    @GET("movie/{id}")
     suspend fun getTheMoviesDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): TheMoviesDetailDto

    @GET("search/movie")
    suspend fun searchTheMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY,
    ): TheMoviesAllDto

    // Explore Screen İçin,
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY,
    ): TheExplorerMovieListsDto

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY,
    ): TheExplorerMovieDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY,
    ): TheExplorerMovieDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY,
    ): TheExplorerMovieListsDto
}