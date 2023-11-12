package com.halilkrkn.themoviesapp.domain.usecase

import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesDto
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTheMoviesDetailUseCase @Inject constructor(
    private val theMoviesRepository: TheMoviesRepository,
) {
    suspend operator fun invoke(id: Int): Flow<Resource<TheMovies>> = flow {
        try {
            emit(Resource.Loading())
            val theMoviesDetail = theMoviesRepository.getTheMoviesDetail(id).toTheMovies()
        emit(Resource.Success(theMoviesDetail))
    } catch (e: Exception)
    {
        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
    }
}
}