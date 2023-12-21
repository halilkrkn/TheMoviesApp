package com.halilkrkn.themoviesapp.presentation.main.trending.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.domain.model.TheTrendingMovies
import com.halilkrkn.themoviesapp.navigation.screens.DetailsScreen
import com.halilkrkn.themoviesapp.ui.theme.orangeColor
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedAnimatable")
@Composable
fun TrendingMoviesListItemScreen(
    isRefreshing: Boolean,
    theMovies: List<TheTrendingMovies>,
    navController: NavController,
    refresh: () -> Unit,
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            refresh()
        }
    )

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
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
                TrendingMoviesListItem(
                    theMovies = theMovies,
                    modifier = Modifier.fillMaxWidth(),
                    onItemClick = {
                        navController.navigate(
                            DetailsScreen.Detail.route.plus("/${theMovies.id}")
                        )
                    }
                )
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = Color.Black,
            contentColor = orangeColor,
            scale = true
        )
    }
}
