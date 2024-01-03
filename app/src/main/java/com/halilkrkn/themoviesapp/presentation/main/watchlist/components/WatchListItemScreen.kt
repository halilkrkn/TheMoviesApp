package com.halilkrkn.themoviesapp.presentation.main.watchlist.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.core.Constants
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.navigation.screens.DetailsScreen
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar
import com.halilkrkn.themoviesapp.ui.theme.orangeColor
import kotlinx.coroutines.*

@SuppressLint("UnrememberedAnimatable")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WatchListItemScreen(
    isRefreshing: Boolean,
    theMovies: LazyPagingItems<TheMovies>,
    navController: NavController,
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            theMovies.refresh()
        }
    )
    val context = LocalContext.current
    LaunchedEffect(key1 = theMovies.loadState) {
        if (theMovies.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error" + (theMovies.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (theMovies.loadState.refresh is LoadState.Loading) {
            LoadingProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                raw = R.raw.movie_splash_1
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    if (theMovies.loadState.prepend is LoadState.Loading) {
                        LoadingProgressBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            raw = R.raw.movie_splash_1
                        )
                    }
                }
                items(theMovies) { theMovies ->
                    if (theMovies != null) {
                        WatchListItem(
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
                item {
                    if (theMovies.loadState.append is LoadState.Loading) {
                        LoadingProgressBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            raw = R.raw.movie_splash_1
                        )
                    }
                }
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