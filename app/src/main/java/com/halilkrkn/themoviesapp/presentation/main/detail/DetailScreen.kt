package com.halilkrkn.themoviesapp.presentation.main.detail

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.core.Constants.TAG
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar
import com.halilkrkn.themoviesapp.presentation.main.detail.components.DetailScreenItem
import com.halilkrkn.themoviesapp.ui.theme.PurpleGrey80


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    movieId: Int?,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        if (movieId != null) {
            viewModel.onRefresh(movieId)
        }
    }

    Scaffold(
        floatingActionButton = {
            state.theMoviesDetail?.let { theMovies ->
                FloatingActionButton(
                    backgroundColor = PurpleGrey80,
                    onClick = {
                        Log.d(TAG, "DetailScreen: ${theMovies.id}")
                        Toast.makeText(
                            navController.context,
                            "Added " + theMovies.title,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.onFavoriteMovie(theMovies)
                        Log.d("userId", "UserId: " + theMovies.userId)
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .height(50.dp)
                        .width(50.dp),
                ) {
                    if (state.isFavorite) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Add to WatchList",
                            tint = Color.Red
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Add to WatchList",
                            tint = Color.DarkGray
                        )
                    }
                }
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    state.theMoviesDetail?.let { theMovies ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = theMovies.title,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )
                        }
                    }
                },
                navigationIcon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Back")

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            state.theMoviesDetail?.let { theMovies ->
                DetailScreenItem(
                    theMovies = theMovies
                )
            }

            if (state.error.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            if (state.isLoading) {
                LoadingProgressBar(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(48.dp),
                    raw = R.raw.movie_splash_1
                )
            }
        }
    }
}
