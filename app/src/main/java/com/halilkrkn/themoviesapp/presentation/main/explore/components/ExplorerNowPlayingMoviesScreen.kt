package com.halilkrkn.themoviesapp.presentation.main.explore.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar
import kotlinx.coroutines.launch

@Composable
fun ExplorerNowPlayingMoviesScreen(
    theMovies: List<TheMovies>,
    navController: NavController,
    state : Boolean
) {

    val lazyListState = rememberLazyListState()
    var isShowingMore by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxHeight(),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            if (state) {
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
            Text(
                text = "Now Playing",
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, top = 24.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
            )
            LazyRow(
                state = lazyListState,
                modifier = Modifier
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(theMovies.take(if (isShowingMore) theMovies.size else 5)) { theMovies ->
                    TheExplorerMoviesItem(
                        theMovies = theMovies,
                        onItemClick = {
                            Toast.makeText(
                                navController.context,
                                "Clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                            /*  Log.d(Constants.TAG, "DetailScreen: ${theMovies.id}")
                              navController.navigate(
                                  DetailsScreen.Detail.route.plus("/${theMovies.id}")
                              )*/
                        }
                    )
                }
                if (!isShowingMore) {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "Daha Fazla",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable {
                                        lazyListState.firstVisibleItemIndex.let {
                                            scope.launch {
                                                lazyListState.scrollToItem(it)
                                            }
                                        }
                                        isShowingMore = true
                                    },
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
        if (isShowingMore) {
            DisposableEffect(lazyListState) {
                scope.launch {
                    lazyListState.scrollToItem(lazyListState.firstVisibleItemIndex)
                }
                onDispose { }
            }
        }
    }
}

