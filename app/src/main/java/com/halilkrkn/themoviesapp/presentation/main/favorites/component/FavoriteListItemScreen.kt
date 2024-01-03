package com.halilkrkn.themoviesapp.presentation.main.favorites.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.core.Constants
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.navigation.screens.DetailsScreen

@Composable
fun FavoriteListItemScreen(
    theMovies: List<TheMovies>,
    navController: NavController,
    deleteClick: (TheMovies) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                theMovies,
                key = { item ->
                    item.id
                }
            ) { theMovies ->
                FavoriteListItem(
                    theMovies = theMovies,
                    onItemClick = { movies ->
                        navController.navigate(
                            DetailsScreen.Detail.route.plus("/${movies.id}")
                        )
                    },
                    deleteClick = {
                       deleteClick(theMovies)
                    }
                )
            }
        }
    }
}
