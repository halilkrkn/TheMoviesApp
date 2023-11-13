package com.halilkrkn.themoviesapp.di

import android.content.Context
import androidx.room.Room
import com.halilkrkn.themoviesapp.core.Constants.BASE_URL
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import com.halilkrkn.themoviesapp.data.repository.TheMoviesRepositoryImpl
import com.halilkrkn.themoviesapp.domain.usecase.GetAllTheMoviesUseCase
import com.halilkrkn.themoviesapp.domain.usecase.GetSearchTheMoviesUseCase
import com.halilkrkn.themoviesapp.domain.usecase.GetTheMoviesDetailUseCase
import com.halilkrkn.themoviesapp.domain.usecase.TheMoviesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
    fun provideTheMoviesRepository(
        api: TheMoviesApi,
        db: TheMoviesDatabase
    ): TheMoviesRepository {
        return TheMoviesRepositoryImpl(api, db)
    }

    @Singleton
    @Provides
    fun provideTheMoviesUseCases(repository: TheMoviesRepository): TheMoviesUseCases {
        return TheMoviesUseCases(
            getAllTheMoviesUseCase = GetAllTheMoviesUseCase(repository),
            getTheMoviesDetailUseCase = GetTheMoviesDetailUseCase(repository),
            getSearchTheMoviesUseCase = GetSearchTheMoviesUseCase(repository)
        )
    }

}

//TheMoviesDetailDto