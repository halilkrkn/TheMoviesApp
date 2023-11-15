package com.halilkrkn.themoviesapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.`when`

class GetTheMoviesDetailUseCaseTest {
}