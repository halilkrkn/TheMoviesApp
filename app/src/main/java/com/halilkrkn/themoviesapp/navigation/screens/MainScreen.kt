package com.halilkrkn.themoviesapp.navigation.screens

sealed class MainScreen(val route: String) {
    object Main: MainScreen("main")
}
