package com.halilkrkn.themoviesapp.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.halilkrkn.themoviesapp.navigation.screens.BottomBarScreen
import com.halilkrkn.themoviesapp.navigation.screens.DetailsScreen
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import com.halilkrkn.themoviesapp.presentation.main.detail.DetailScreen
import com.halilkrkn.themoviesapp.presentation.main.explore.ExploreScreen
import com.halilkrkn.themoviesapp.presentation.main.favorites.FavoritesScreen
import com.halilkrkn.themoviesapp.presentation.main.search.SearchScreen
import com.halilkrkn.themoviesapp.presentation.main.trending.TrendingScreen
import com.halilkrkn.themoviesapp.presentation.main.watchlist.WatchListScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.TrendingMoviesScreen.route,
        route = Graphs.MAIN,
        modifier = modifier
    ) {

        composable(route = BottomBarScreen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

        composable(route = BottomBarScreen.WatchlistScreen.route) {
            WatchListScreen(navController = navController)
        }

        composable(route = BottomBarScreen.FavoritesScreen.route) {
            FavoritesScreen(navController = navController)
        }

        composable(route = BottomBarScreen.ExploreScreen.route) {
            ExploreScreen(navController = navController)
        }

        composable(route = BottomBarScreen.TrendingMoviesScreen.route) {
            TrendingScreen(navController = navController)
        }
        detailsNavGraph(navController = navController)
        authNavGraph(navController = navController)
    }
}