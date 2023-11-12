package com.halilkrkn.themoviesapp.presentation.main.detail

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.core.Constants
import com.halilkrkn.themoviesapp.core.Constants.TAG
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar
import com.halilkrkn.themoviesapp.presentation.main.detail.components.DetailScreenItem
import com.halilkrkn.themoviesapp.ui.theme.moreLightBlue
import com.halilkrkn.themoviesapp.ui.theme.StarColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    movieId : Int?,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit){
        if (movieId != null) {
            viewModel.onRefresh(movieId)
        }
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            state.theMoviesDetail?.let {
                FloatingActionButton(
                    onClick = {
                        Log.d(TAG, "DetailScreen: ${it.id}")
                        Toast.makeText(
                            navController.context,
                            "DetailScreen: ${it.id}",
                            Toast.LENGTH_SHORT
                        ).show()
                        //viewModel.onAddToWatchList(it)
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .height(50.dp)
                        .width(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Add to WatchList",
                        tint = moreLightBlue
                    )
                }
            }

        },
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        state.theMoviesDetail?.let {
                            Text(
                                text = "Detaylar",
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
                        Text(text = "Geri")
                    }
                }
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
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
}

/*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenItem(
    theMovies: TheMovies,
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        DetailScreenItemPoster(
            theMovies = theMovies,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        DetailScreenInformation(
            theMovies = theMovies,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DetailScreenItemPoster(theMovies: TheMovies, modifier: Modifier) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 5.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            AsyncImage(
                model = Constants.IMAGE_BASE_URL + theMovies.posterPath,
                contentDescription = theMovies.title,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .requiredHeight(300.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 400f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                DetailScreenItemOverview(
                    theMovies = theMovies
                )
            }
        }
    }
}

@Composable
fun DetailScreenItemOverview(theMovies: TheMovies) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Release Date",
                tint = StarColor
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = theMovies.voteAverage.toString(),
                color = Color.White
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = theMovies.releaseDate,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Release Date",
                tint = moreLightBlue
            )
        }
    }
}

@Composable
fun DetailScreenInformation(
    theMovies: TheMovies,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = theMovies.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = theMovies.overview,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = Color.Black,
                textDirection = TextDirection.ContentOrLtr
            )
        )
    }
}*/
