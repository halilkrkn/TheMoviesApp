package com.halilkrkn.themoviesapp.domain.repository

import androidx.paging.Pager
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesDto
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesPageDto
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import kotlinx.coroutines.flow.Flow

interface TheMoviesRepository {
    fun getAllTheMovies(): Pager<Int, TheMoviesEntity>
    suspend fun getTheMoviesDetail(id: Int): TheMoviesDetailDto
    suspend fun searchTheMovies(query: String): TheMoviesPageDto

}

