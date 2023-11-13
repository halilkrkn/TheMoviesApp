package com.halilkrkn.themoviesapp.domain.repository

import androidx.paging.Pager
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesPageDto
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import kotlinx.coroutines.flow.Flow

interface TheMoviesRepository {

    // Network Operations
    fun getAllTheMovies(): Pager<Int, TheMoviesEntity>
    suspend fun getTheMoviesDetail(id: Int): TheMoviesDetailDto
    suspend fun searchTheMovies(query: String): TheMoviesPageDto

    // Database Operations
    suspend fun insertFavorite(theMovies: TheMoviesFavoriteEntity)
    suspend fun deleteFavorite(theMovies: TheMoviesFavoriteEntity)
    fun getAllFavorites(): Flow<List<TheMoviesFavoriteEntity>>
    fun searchFavorite(searchQuery: String): Flow<List<TheMoviesFavoriteEntity>>

}

