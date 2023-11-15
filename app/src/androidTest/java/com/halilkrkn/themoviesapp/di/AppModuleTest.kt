package com.halilkrkn.themoviesapp.di

import android.content.Context
import androidx.room.Room
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.junit.Assert.*
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
class AppModuleTest {

    @Provides
    @Named("the_movie_test_db")
    fun provideInMemoryTheMoviesDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context,
            TheMoviesDatabase::class.java
        ).build()

}