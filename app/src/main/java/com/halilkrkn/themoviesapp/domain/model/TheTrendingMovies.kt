package com.halilkrkn.themoviesapp.domain.model

import com.google.firebase.auth.FirebaseAuth
import com.google.gson.annotations.SerializedName

data class TheTrendingMovies(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val mediaType: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
//    val genreIds: List<Int>,
)
