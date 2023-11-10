package com.halilkrkn.themoviesapp.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.halilkrkn.themoviesapp.core.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
}