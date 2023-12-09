package com.halilkrkn.themoviesapp.presentation.main.trending

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar
import com.halilkrkn.themoviesapp.presentation.main.trending.components.TrendingMoviesListItemScreen
import com.halilkrkn.themoviesapp.ui.theme.orangeColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TrendingScreen(
    navController: NavController,
    trendingMoviesViewModel: TrendingMoviesViewModel = hiltViewModel(),
) {
    val state = trendingMoviesViewModel.state.value
    val theMovies = state.theTrendingMovies
    val isRefreshing by trendingMoviesViewModel.isRefreshing.collectAsStateWithLifecycle()
    val isLoading = state.isLoading
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TopAppBar(
                    title = { Text(text = "Trending") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 60.dp, start = 16.dp, end = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            InputChipMovies(
                onClickDaily = {
                    scope.launch {
                        trendingMoviesViewModel.getDailyMovies()
                    }
                },
                onClickWeekly = {
                    trendingMoviesViewModel.getWeeklyMovies()
                }
            )
            TrendingMoviesListItemScreen(
                theMovies = theMovies,
                navController = navController,
                isRefreshing = isRefreshing
            )
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                LoadingProgressBar(
                    raw = R.raw.movie_splash_1
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun InputChipMovies(
    onClickDaily: () -> Unit,
    onClickWeekly: () -> Unit,
) {
    val itemsList = listOf("Day", "Week")

    var selectedItem by remember {
        mutableStateOf(itemsList[0])
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        items(itemsList) { item ->
            InputChip(
                shape = CircleShape,
                border = InputChipDefaults.inputChipBorder(
                    borderColor = orangeColor,
                    borderWidth = 2.dp
                ),
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = item == selectedItem,
                onClick = {
                    selectedItem = item
                    if (item == "Day")
                        onClickDaily()
                    else if (item == "Week") {
                        onClickWeekly()
                    }
                },
                label = {
                    Text(
                        text = item,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            letterSpacing = MaterialTheme.typography.labelLarge.letterSpacing,
                            fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                        )
                    )
                }
            )
        }
    }
}