package com.halilkrkn.themoviesapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.halilkrkn.themoviesapp.navigation.screens.DetailsScreen
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import com.halilkrkn.themoviesapp.presentation.main.detail.DetailScreen

fun NavGraphBuilder.detailsNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = DetailsScreen.Detail.route,
        route = Graphs.DETAILS
    ) {
        composable(
            route = DetailsScreen.Detail.route + "/{movieId}",arguments = listOf(navArgument("movieId") {
                defaultValue = -1
                type = NavType.IntType },
            )) {navBackStackEntry ->  DetailScreen(navController = navController, movieId = navBackStackEntry.arguments?.getInt("movieId"))
        }
//        ) {
//            DetailScreen(navController = navController, movieId = it.arguments?.getInt("movieId"))
//        }
    }
}