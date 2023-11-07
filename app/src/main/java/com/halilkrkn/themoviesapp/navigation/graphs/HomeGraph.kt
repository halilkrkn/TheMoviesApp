package com.halilkrkn.themoviesapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.halilkrkn.themoviesapp.navigation.screens.MainScreen
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import com.halilkrkn.themoviesapp.presentation.main.MainScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = MainScreen.Main.route,
        route = Graphs.MAIN
    ) {
        composable(
            route = MainScreen.Main.route
        ) {
            MainScreen()
        }
    }
}