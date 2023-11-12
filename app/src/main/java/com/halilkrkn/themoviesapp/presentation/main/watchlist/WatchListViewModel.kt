package com.halilkrkn.themoviesapp.presentation.main.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.domain.usecase.GetAllTheMoviesUseCase
import com.halilkrkn.themoviesapp.domain.usecase.TheMoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val theMoviesUseCases: TheMoviesUseCases,
) : ViewModel() {

    val getAllTheMovies =
        theMoviesUseCases.getAllTheMoviesUseCase().flow
            .map {
                it.map { entity ->
                    entity.toTheMovies()
                }
            }.cachedIn(viewModelScope)
    }
