package com.halilkrkn.themoviesapp.presentation.main.explore.state

import com.halilkrkn.themoviesapp.domain.model.TheExplorerMovieLists
import com.halilkrkn.themoviesapp.domain.model.TheMovies

data class TheExplorerNowPlayingMoviesState(
    val isLoading: Boolean = false,
    val theExplorerMovies: List<TheMovies> = emptyList(),
    val error: String = "",
)
