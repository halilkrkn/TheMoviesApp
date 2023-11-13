package com.halilkrkn.themoviesapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import com.halilkrkn.themoviesapp.data.paging.PagingTheMoviesMediator
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesPageDto
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TheMoviesRepositoryImpl @Inject constructor(
    private val theMoviesApi: TheMoviesApi,
    private val theMoviesDatabase: TheMoviesDatabase
) : TheMoviesRepository {

    // Network Operations
    override fun getAllTheMovies(): Pager<Int, TheMoviesEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false,
            ),
            remoteMediator = PagingTheMoviesMediator(
                theMoviesDatabase,
                theMoviesApi
            ),
            pagingSourceFactory = { theMoviesDatabase.theMoviesDao().getAllTheMovies() }
        )
    }

    override suspend fun getTheMoviesDetail(id: Int): TheMoviesDetailDto {
        return theMoviesApi.getTheMoviesDetail(id)
    }

    override suspend fun searchTheMovies(query: String): TheMoviesPageDto {
         return theMoviesApi.searchTheMovies(query)

    }

    // Database Operations
    override suspend fun insertFavorite(theMovies: TheMoviesFavoriteEntity) {
        return theMoviesDatabase.theMoviesFavoriteDao().insert(theMovies)
    }

    override suspend fun deleteFavorite(theMovies: TheMoviesFavoriteEntity) {
        theMoviesDatabase.theMoviesFavoriteDao().delete(theMovies)
    }

    override fun getAllFavorites(): Flow<List<TheMoviesFavoriteEntity>> {
        return theMoviesDatabase.theMoviesFavoriteDao().getAllFavorite()
    }

    override fun searchFavorite(searchQuery: String): Flow<List<TheMoviesFavoriteEntity>> {
        return theMoviesDatabase.theMoviesFavoriteDao().searchFavorite(searchQuery)
    }
}