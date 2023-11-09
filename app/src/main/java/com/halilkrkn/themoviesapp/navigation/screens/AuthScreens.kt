package com.halilkrkn.themoviesapp.navigation.screens

sealed class AuthScreens(val route: String) {
    object SignIn : AuthScreens("signIn")
    object SignUp : AuthScreens("signUp")
}