package com.halilkrkn.themoviesapp.presentation.main.favorites

import com.halilkrkn.themoviesapp.domain.model.TheMovies

data class TheMoviesFavoriteState (
    val isLoading: Boolean = false,
    val theMovies: List<TheMovies> = emptyList(),
    val error: String = ""
)