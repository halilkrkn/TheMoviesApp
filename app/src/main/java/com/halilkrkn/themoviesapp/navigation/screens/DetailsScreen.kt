package com.halilkrkn.themoviesapp.navigation.screens

sealed class DetailsScreen(val route: String) {
    object Detail: DetailsScreen("detail")
}
