package com.halilkrkn.themoviesapp.domain.model

import com.google.firebase.auth.FirebaseAuth

data class TheMovies(
    val id: Int,
    val userId: String = FirebaseAuth.getInstance().currentUser?.uid.toString(),
    val adult: Boolean,
    val backdropPath: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
//    val genreIds: List<Int>,
)