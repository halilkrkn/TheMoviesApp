package com.halilkrkn.themoviesapp.data.remote.dto.explore


import com.google.gson.annotations.SerializedName

data class TheExplorerMovieListsDto(
    @SerializedName("dates")
    val datesDto: DatesDto,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val theExplorerMovieDtos: List<TheExplorerMovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)