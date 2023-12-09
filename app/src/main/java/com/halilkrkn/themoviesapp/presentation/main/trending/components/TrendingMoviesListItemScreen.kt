package com.halilkrkn.themoviesapp.presentation.main.trending.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.core.Constants
import com.halilkrkn.themoviesapp.domain.model.TheTrendingMovies
import com.halilkrkn.themoviesapp.navigation.screens.DetailsScreen
import kotlinx.coroutines.*

@SuppressLint("UnrememberedAnimatable")
@Composable
fun TrendingMoviesListItemScreen(
    isRefreshing: Boolean,
    theMovies: List<TheTrendingMovies>,
    navController: NavController,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(theMovies) { theMovies ->
                if (theMovies != null) {
                    TrendingMoviesListItem(
                        theMovies = theMovies,
                        modifier = Modifier.fillMaxWidth(),
                        onItemClick = {
                            Log.d(Constants.TAG, "DetailScreen: ${theMovies.id}")
                            navController.navigate(
                                DetailsScreen.Detail.route.plus("/${theMovies.id}")
                            )
                        }
                    )
                }
            }
        }
    }
}
