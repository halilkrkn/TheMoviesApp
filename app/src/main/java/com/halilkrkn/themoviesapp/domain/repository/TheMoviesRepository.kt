package com.halilkrkn.themoviesapp.domain.repository

import androidx.paging.Pager
import com.halilkrkn.themoviesapp.data.local.dao.TheMoviesDao
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.paging.TheMoviesSearchPagingSource
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import javax.inject.Inject

interface TheMoviesRepository {
    fun getAllTheMovies(): Pager<Int,TheMoviesEntity>
}