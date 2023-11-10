package com.halilkrkn.themoviesapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.halilkrkn.themoviesapp.core.Constants.BASE_URL
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTheMoviesApi(retrofit: Retrofit): TheMoviesApi {
        return retrofit.create(TheMoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTheMoviesDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        TheMoviesDatabase::class.java,
        "movies_db"
    ).build()

    @Singleton
    @Provides
    fun provideTheMoviesDao(db: TheMoviesDatabase) = db.theMoviesDao()

}