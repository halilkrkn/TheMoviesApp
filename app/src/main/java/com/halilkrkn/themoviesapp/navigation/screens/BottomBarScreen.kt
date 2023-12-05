package com.halilkrkn.themoviesapp.navigation.screens

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ManageSearch
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.halilkrkn.themoviesapp.R

sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object WatchlistScreen : BottomBarScreen(
        route = "watchlist",
        icon = Icons.Default.List,
        title = "Watchlist"
    )
    object SearchScreen : BottomBarScreen(
        route = "search",
        icon = Icons.Filled.ManageSearch,
        title = "Search"
    )
    object FavoritesScreen : BottomBarScreen(
        route = "favorites",
        icon = Icons.Filled.Favorite,
        title = "Favorites"
    )

    object ExploreScreen : BottomBarScreen(
        route = "explore",
        icon = Icons.Filled.Explore,
        title = "Explore"
    )
}