package com.halilkrkn.themoviesapp.presentation.main.watchlist

import androidx.lifecycle.ViewModel
import androidx.paging.map
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.domain.usecase.GetAllTheMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val theMoviesUseCases: GetAllTheMoviesUseCase,
) : ViewModel() {

    val getAllTheMovies = theMoviesUseCases().flow
        .map {
            it.map { entity ->
                entity.toTheMovies()
            }
        }
}
