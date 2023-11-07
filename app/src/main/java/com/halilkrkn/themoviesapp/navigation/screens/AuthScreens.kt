package com.halilkrkn.themoviesapp.navigation.screens

sealed class AuthScreens(val route: String) {
    object Splash : AuthScreens("splash")
    object Login : AuthScreens("login")
    object Register : AuthScreens("register")
}