package com.halilkrkn.themoviesapp.presentation.main.explore

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar
import com.halilkrkn.themoviesapp.presentation.main.explore.components.ExplorerNowPlayingMoviesScreen
import com.halilkrkn.themoviesapp.presentation.main.explore.components.ExplorerPopularMoviesScreen
import com.halilkrkn.themoviesapp.ui.theme.orangeColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ExploreScreen(
    navController: NavController,
    viewModel: TheExplorerMoviesViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value
    val theMovies = state.theExplorerMovies
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            viewModel.onRefresh()
        }
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Explorer Movies",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = Color.Black
                            )
                        )
                    }

                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 24.dp, start = 8.dp, end = 8.dp)
                    .pullRefresh(pullRefreshState)
                    .fillMaxSize(),
            ) {
                item {
                    if (state.isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingProgressBar(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                raw = R.raw.movie_splash_1
                            )
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                    ) {
                        Text(
                            text = "",
                            modifier = Modifier
                                .padding(start = 4.dp, end = 4.dp)
                                .fillMaxWidth(),
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize
                            )
                        )
                        ExplorerNowPlayingMoviesScreen(
                            theMovies = theMovies,
                            navController = navController,
                        )
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
                item {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                    ) {
                        Text(
                            text = "",
                            modifier = Modifier
                                .padding(start = 4.dp, end = 4.dp)
                                .fillMaxWidth(),
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize
                            )
                        )
                        ExplorerPopularMoviesScreen(
                            theMovies = theMovies,
                            navController = navController,
                        )
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
            }
        },
    )
}