package com.halilkrkn.themoviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.halilkrkn.themoviesapp.data.local.dao.TheMoviesDao
import com.halilkrkn.themoviesapp.data.local.dao.TheMoviesFavoriteDao
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity

@Database(
    entities = [
        TheMoviesEntity::class,
        TheMoviesFavoriteEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TheMoviesDatabase : RoomDatabase() {
    abstract fun theMoviesDao(): TheMoviesDao
    abstract fun theMoviesFavoriteDao(): TheMoviesFavoriteDao
}