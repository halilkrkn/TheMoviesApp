package com.halilkrkn.themoviesapp.data.remote.dto.trending


import com.google.gson.annotations.SerializedName

data class TrendingMoviesDtos(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingMovieDtos: List<TrendingMoviesDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)