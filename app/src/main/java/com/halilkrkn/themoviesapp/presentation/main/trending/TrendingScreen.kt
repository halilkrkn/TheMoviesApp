package com.halilkrkn.themoviesapp.presentation.main.trending

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
                isRefreshing = isRefreshing,
                refresh = { trendingMoviesViewModel.onRefreshDailyMovies() }
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


@Composable
fun InputChipMovies(
    onClickDaily: () -> Unit,
    onClickWeekly: () -> Unit,
) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        item {
            SuggestionChip(
                shape = CircleShape,
                border = SuggestionChipDefaults.suggestionChipBorder(
                    borderColor = orangeColor,
                    borderWidth = 2.dp
                ),
                modifier = Modifier.padding(horizontal = 6.dp),
                onClick = {
                    onClickDaily()
                },
                label = {
                    Text(
                        text = "Daily",
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
        item {
            SuggestionChip(
                shape = CircleShape,
                border = SuggestionChipDefaults.suggestionChipBorder(
                    borderColor = orangeColor,
                    borderWidth = 2.dp
                ),
                modifier = Modifier.padding(horizontal = 6.dp),
                onClick = {
                    onClickWeekly()
                },
                label = {
                    Text(
                        text = "Weekly",
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
