package com.halilkrkn.themoviesapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.halilkrkn.themoviesapp.navigation.screens.AuthScreens
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import com.halilkrkn.themoviesapp.presentation.auth.login.LoginScreen
import com.halilkrkn.themoviesapp.presentation.auth.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = AuthScreens.Login.route,
        route = Graphs.AUTHENTICATION
    ) {

        composable(
            route = AuthScreens.Login.route
        ) {
            LoginScreen(navController = navController)
        }

        composable(
            route = AuthScreens.Register.route
        ) {
            RegisterScreen(navController = navController)
        }
    }
}