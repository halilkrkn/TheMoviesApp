package com.halilkrkn.themoviesapp.presentation.main.detail

import com.halilkrkn.themoviesapp.domain.model.TheMovies

data class TheMoviesDetailState(
    val isLoading: Boolean = false,
    val theMoviesDetail: TheMovies? = null,
    val error: String = ""
)
