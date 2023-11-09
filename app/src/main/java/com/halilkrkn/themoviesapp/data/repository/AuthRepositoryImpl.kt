package com.halilkrkn.themoviesapp.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(authResult))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(authResult))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override suspend fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }
}