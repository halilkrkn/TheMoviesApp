package com.halilkrkn.themoviesapp.presentation.main.search.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.halilkrkn.themoviesapp.core.Constants
import com.halilkrkn.themoviesapp.core.Constants.TAG
import com.halilkrkn.themoviesapp.domain.model.TheMovies

@Composable
fun SearchMoviesItem(
    theMovies: TheMovies,
    onItemClick: (TheMovies) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onItemClick(theMovies)
                }
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = Constants.IMAGE_BASE_URL + theMovies.posterPath,
                contentDescription = theMovies.title,
                modifier = Modifier
                    .requiredHeight(90.dp)
            )

            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = theMovies.title + " - " + theMovies.releaseDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                softWrap = false
            )
        }
    }
}

@Preview
@Composable
fun SearchMoviesItemPreview() {
    SearchMoviesItem(
        theMovies = TheMovies(
            id = 1,
            adult = false,
            backdropPath = "",
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            releaseDate = "2023",
            title = "Wall Street",
            video = false,
            voteAverage = 0.0,
            voteCount = 0
        ),
        onItemClick = {}
    )

}