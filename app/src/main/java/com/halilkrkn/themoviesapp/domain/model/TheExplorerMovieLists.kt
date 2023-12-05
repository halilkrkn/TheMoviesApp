package com.halilkrkn.themoviesapp.domain.model

import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.themoviesapp.data.remote.dto.explore.DatesDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieDto

data class TheExplorerMovieLists(
    val datesDto: DatesDto,
    val page: Int,
    val theExplorerMovieDtos: List<TheExplorerMovieDto>,
    val totalPages: Int,
    val totalResults: Int,
    val userId: String = FirebaseAuth.getInstance().currentUser?.uid.toString(),
)
