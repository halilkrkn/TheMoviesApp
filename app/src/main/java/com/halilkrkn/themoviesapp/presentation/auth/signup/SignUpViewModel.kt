package com.halilkrkn.themoviesapp.presentation.auth.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _signUpState = mutableStateOf<SignUpState>(SignUpState())
    val signUpState: State<SignUpState> = _signUpState

    fun signUpWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            repository.signUpWithEmailAndPassword(email, password).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _signUpState.value = SignUpState(isSuccess = " Sign Up Success")
                    }

                    is Resource.Loading -> {
                        _signUpState.value = SignUpState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _signUpState.value = SignUpState(isError = result.message)
                    }
                }
            }
        }
    }
}