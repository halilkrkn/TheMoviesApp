package com.halilkrkn.themoviesapp.domain.repository

import androidx.paging.Pager
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto

interface TheMoviesRepository {
    fun getAllTheMovies(): Pager<Int,TheMoviesEntity>
    suspend fun getTheMoviesDetail(id: Int): TheMoviesDetailDto
}