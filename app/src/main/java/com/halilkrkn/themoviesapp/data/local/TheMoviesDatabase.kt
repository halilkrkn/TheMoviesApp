package com.halilkrkn.themoviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.halilkrkn.themoviesapp.data.local.dao.TheMoviesDao
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity

@Database(
    entities = [TheMoviesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TheMoviesDatabase: RoomDatabase() {
        abstract fun theMoviesDao(): TheMoviesDao
}