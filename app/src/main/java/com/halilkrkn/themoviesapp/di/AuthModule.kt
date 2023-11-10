package com.halilkrkn.themoviesapp.di

import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.themoviesapp.data.repository.AuthRepositoryImpl
import com.halilkrkn.themoviesapp.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepository(
        firebaseAuth: FirebaseAuth,
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }
}