package com.halilkrkn.themoviesapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.halilkrkn.themoviesapp.navigation.screens.SplashScreen
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import com.halilkrkn.themoviesapp.presentation.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = SplashScreen.Splash.route,
        route = Graphs.SPLASH
    ) {

        composable(
            route = SplashScreen.Splash.route
        ) {
            SplashScreen(navController = navController)
        }
    }
}