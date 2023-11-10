package com.halilkrkn.themoviesapp.presentation.auth.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.domain.repository.AuthRepository
import com.halilkrkn.themoviesapp.presentation.auth.signin.util.GoogleSignInState
import com.halilkrkn.themoviesapp.presentation.auth.signin.util.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _signInState = mutableStateOf<SignInState>(SignInState())
    val signInState: State<SignInState> = _signInState

    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    suspend fun signInWithEmailAndPassword(email: String, password: String) =
        viewModelScope.launch {
            authRepository.signInWithEmailAndPassword(email, password).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _signInState.value = SignInState(isSuccess = " Sign Up Success")
                    }

                    is Resource.Loading -> {
                        _signInState.value = SignInState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _signInState.value = SignInState(isError = result.message)
                    }
                }
            }
        }

    suspend fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        authRepository.googleSignIn(credential).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _googleState.value = GoogleSignInState(success = result.data)
                }

                is Resource.Loading -> {
                    _googleState.value = GoogleSignInState(success = result.data)
                }

                is Resource.Error -> {
                    _googleState.value = GoogleSignInState(error = result.message!!)
                }
            }
        }
    }

    fun resetSignInState() {
        _googleState.value = GoogleSignInState(
            success = null,
            loading = false,
            error = ""
        )
    }
}

