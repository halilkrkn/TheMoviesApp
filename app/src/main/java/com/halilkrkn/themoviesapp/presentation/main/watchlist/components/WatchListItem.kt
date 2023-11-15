package com.halilkrkn.themoviesapp.presentation.main.watchlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.halilkrkn.themoviesapp.core.Constants
import com.halilkrkn.themoviesapp.core.Constants.IMAGE_BASE_URL
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.ui.theme.TheMoviesAppTheme


@Composable
fun WatchListItem(
    theMovies: TheMovies,
    modifier: Modifier = Modifier,
    onItemClick: (TheMovies) -> Unit
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
            AsyncImage(
                model = IMAGE_BASE_URL + theMovies.posterPath,
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
                    text = theMovies.originalTitle,
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
fun WatchListItemPreview() {
    TheMoviesAppTheme {
        WatchListItem(
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
                voteCount = 0
            ),
            modifier = Modifier.fillMaxWidth(),
            onItemClick = {}
        )
    }
}