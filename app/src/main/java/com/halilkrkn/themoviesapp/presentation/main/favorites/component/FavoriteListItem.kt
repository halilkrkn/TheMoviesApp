package com.halilkrkn.themoviesapp.presentation.main.favorites.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.core.Constants.IMAGE_BASE_URL
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar
import com.halilkrkn.themoviesapp.ui.theme.TheMoviesAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteListItem(
    theMovies: TheMovies,
    onItemClick: (TheMovies) -> Unit,
    deleteClick: (TheMovies) -> Unit,
) {

    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                deleteClick(theMovies)
            }
            it != DismissValue.DismissedToEnd
        },
        positionalThreshold = { 150.dp.toPx() }
    )

    SwipeToDismiss(
        state = dismissState,
        background = {
            DismissBackground(
                dismissState = dismissState
            )
        },
        directions = setOf(DismissDirection.EndToStart),
        dismissContent = {
            FavoriteItem(
                theMovies = theMovies,
                onItemClick = { theMovies ->
                    onItemClick(theMovies)
                },
                onDelete = {
                    deleteClick(theMovies)
                },
            )
        }
    )
}


@Composable
fun FavoriteItem(
    theMovies: TheMovies,
    onItemClick: (TheMovies) -> Unit,
    modifier: Modifier = Modifier,
    onDelete: (TheMovies) -> Unit = {},
) {
    Card(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onItemClick(theMovies)
                }
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)

        ) {
            SubcomposeAsyncImage(
                model = IMAGE_BASE_URL + theMovies.posterPath,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(100.dp, 100.dp)
                            .background(Color.Black.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingProgressBar(
                            modifier = Modifier
                                .size(50.dp, 50.dp),
                            raw = R.raw.image_loading
                        )
                    }
                },
                error = {
                    LoadingProgressBar(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(50.dp, 50.dp)
                            .padding(12.dp),
                        raw = R.raw.image_error
                    )
                },
                contentDescription = theMovies.title,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = theMovies.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Preview
@Composable
fun SearchListItemPreview() {
    TheMoviesAppTheme {
        FavoriteListItem(
            theMovies = TheMovies(
                id = 1,
                adult = false,
                backdropPath = "",
                originalLanguage = "",
                originalTitle = "Hızlı ve Öfkeli 9",
                overview = "",
                popularity = 0.0,
                posterPath = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
                releaseDate = "",
                title = "Hızlı ve Öfkeli 9",
                video = false,
                voteAverage = 0.0,
                voteCount = 0,
                userId = FirebaseAuth.getInstance().uid.toString()
            ),
            onItemClick = {},
            deleteClick = {}
        )
    }
}