package com.halilkrkn.themoviesapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.paging.PagingTheMoviesMediator
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TheMoviesRepositoryImpl @Inject constructor(
    private val theMoviesApi: TheMoviesApi,
    private val theMoviesDatabase: TheMoviesDatabase
) : TheMoviesRepository {

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

}