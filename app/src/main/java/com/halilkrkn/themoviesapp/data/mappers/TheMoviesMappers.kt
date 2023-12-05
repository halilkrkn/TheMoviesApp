package com.halilkrkn.themoviesapp.data.mappers

import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesDto
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieListsDto
import com.halilkrkn.themoviesapp.domain.model.TheExplorerMovieLists
import com.halilkrkn.themoviesapp.domain.model.TheMovies

fun TheMoviesDto.toTheMoviesEntity(): TheMoviesEntity {
    return TheMoviesEntity(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        userId = null
    )
}

fun TheMoviesEntity.toTheMovies(): TheMovies {
    return TheMovies(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
    )
}

fun TheMoviesDto.toTheMovies(): TheMovies {
    return TheMovies(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
    )
}

fun TheMovies.toTheMoviesDto(): TheMoviesDto {
    return TheMoviesDto(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun TheMoviesDetailDto.toTheMovies(): TheMovies {
    return TheMovies(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
    )
}

fun TheMoviesFavoriteEntity.toTheMovies(): TheMovies {
    return TheMovies(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        userId = userId
    )
}

fun TheMovies.toTheMoviesFavoriteEntity(): TheMoviesFavoriteEntity {
    return TheMoviesFavoriteEntity(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        userId = userId
    )
}

fun TheExplorerMovieDto.toTheMovies(): TheMovies {
    return TheMovies(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
    )
}

fun TheMovies.toTheExploreMovieDto(): TheExplorerMovieDto {
    return TheExplorerMovieDto(
        id = id,
        adult = adult,
        backdropPath = backdropPath!!,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath!!,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun TheExplorerMovieListsDto.toTheExplorerMovieLists(): TheExplorerMovieLists {
    return TheExplorerMovieLists(
        datesDto = datesDto,
        page = page,
        theExplorerMovieDtos = theExplorerMovieDtos,
        totalPages = totalPages,
        totalResults = totalResults,
        userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

    )
}

fun TheExplorerMovieLists.toTheExplorerMoviesDto(): TheExplorerMovieListsDto {
    return TheExplorerMovieListsDto(
        datesDto = datesDto,
        page = page,
        theExplorerMovieDtos = theExplorerMovieDtos,
        totalPages = totalPages,
        totalResults = totalResults
    )
}