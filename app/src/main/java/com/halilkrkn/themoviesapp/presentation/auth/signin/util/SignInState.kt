package com.halilkrkn.themoviesapp.presentation.auth.signin.util

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)