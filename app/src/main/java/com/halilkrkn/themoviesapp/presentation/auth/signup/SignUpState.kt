package com.halilkrkn.themoviesapp.presentation.auth.signup

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = "",
)