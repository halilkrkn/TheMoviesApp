package com.halilkrkn.themoviesapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TheMoviesPageDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val theMoviesDtos: List<TheMoviesDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)