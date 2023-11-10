package com.halilkrkn.themoviesapp.domain.usecase

import androidx.paging.Pager
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import javax.inject.Inject

class GetAllTheMoviesUseCase @Inject constructor(
    private val theMoviesRepository: TheMoviesRepository
) {
    operator fun invoke(): Pager<Int, TheMoviesEntity> {
        return theMoviesRepository.getAllTheMovies()
    }
}


