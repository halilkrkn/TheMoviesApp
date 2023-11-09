package com.halilkrkn.themoviesapp.navigation.graphs

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.halilkrkn.themoviesapp.navigation.screens.AuthScreens
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import com.halilkrkn.themoviesapp.presentation.auth.signin.SignInScreen
import com.halilkrkn.themoviesapp.presentation.auth.signup.SignUpScreen

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = AuthScreens.SignIn.route,
        route = Graphs.AUTHENTICATION
    ) {

        composable(
            route = AuthScreens.SignIn.route
        ) {
            SignInScreen(navController = navController)
        }

        composable(
            route = AuthScreens.SignUp.route
        ) {
            SignUpScreen(navController = navController)
        }
    }
}