package com.halilkrkn.themoviesapp.navigation.screens

sealed class SplashScreen (val route: String) {
    object Splash : SplashScreen("splash")
}
