package com.halilkrkn.themoviesapp.domain.usecase

import android.util.Log
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesDto
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchTheMoviesUseCase @Inject constructor(
    private val theMoviesRepository: TheMoviesRepository,
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<TheMoviesDto>>> = flow {
        emit(Resource.Loading())
        val response = theMoviesRepository.searchTheMovies(query)
        val theMovies = response.theMoviesDtos
        Log.d("Invoke", "invoke: $theMovies")
        emit(Resource.Success(theMovies))
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
    }
}
