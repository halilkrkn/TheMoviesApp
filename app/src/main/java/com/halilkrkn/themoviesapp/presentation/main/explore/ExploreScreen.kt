package com.halilkrkn.themoviesapp.presentation.main.explore

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.halilkrkn.themoviesapp.presentation.main.explore.components.ExplorerTopRatedMoviesScreen
import com.halilkrkn.themoviesapp.presentation.main.explore.components.ExplorerUpComingMoviesScreen
import com.halilkrkn.themoviesapp.ui.theme.orangeColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ExploreScreen(
    navController: NavController,
    viewModel: TheExplorerMoviesViewModel = hiltViewModel(),
) {

    val stateNowPlaying = viewModel.stateNowPlaying.value
    val statePopular = viewModel.statePopular.value
    val stateTopRated = viewModel.stateTopRated.value
    val stateUpcoming = viewModel.stateUpComing.value
    val theNowPlayingMovies = stateNowPlaying.theExplorerMovies
    val thePopularMovies = statePopular.theExplorerMovies
    val theTopRatedMovies = stateTopRated.theExplorerMovies
    val theUpcomingMovies = stateUpcoming.theExplorerMovies
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val state = stateNowPlaying.isLoading && statePopular.isLoading && stateTopRated.isLoading && stateUpcoming.isLoading


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
                    .padding(top = 18.dp, start = 4.dp, end = 4.dp)
                    .pullRefresh(pullRefreshState)
                    .fillMaxSize(),
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        Spacer(modifier = Modifier.height(2.dp))
                        ExplorerNowPlayingMoviesScreen(
                            theMovies = theNowPlayingMovies,
                            navController = navController,
                            state = stateNowPlaying.isLoading
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        Spacer(modifier = Modifier.height(2.dp))
                        ExplorerPopularMoviesScreen(
                            theMovies = thePopularMovies,
                            navController = navController,
                            state = statePopular.isLoading,
                        )

                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        Spacer(modifier = Modifier.height(2.dp))
                        ExplorerTopRatedMoviesScreen(
                            theMovies = theTopRatedMovies,
                            navController = navController,
                            state = stateTopRated.isLoading,
                        )

                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        Spacer(modifier = Modifier.height(2.dp))
                        ExplorerUpComingMoviesScreen(
                            theMovies = theUpcomingMovies,
                            navController = navController,
                            state = stateUpcoming.isLoading,
                        )

                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    backgroundColor = Color.Black,
                    contentColor = orangeColor,
                    scale = true
                )
            }
        },
    )
}