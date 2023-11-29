package com.halilkrkn.themoviesapp.presentation.main.search.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.core.Constants
import com.halilkrkn.themoviesapp.core.Constants.TAG
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar

@Composable
fun SearchMoviesItem(
    theMovies: TheMovies,
    onItemClick: (TheMovies) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
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
            SubcomposeAsyncImage(
                model = Constants.IMAGE_BASE_URL + theMovies.posterPath,
                loading = {
                    Box(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                       LoadingProgressBar(
                           modifier = Modifier,
                           raw = R.raw.image_loading
                       )
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingProgressBar(
                            modifier = Modifier,
                            raw = R.raw.image_error
                        )
                    }
                },
                contentDescription = theMovies.title,
                modifier = Modifier
                    .wrapContentHeight(CenterVertically),
                )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(CenterVertically)
            ){
                Text(
                    modifier = Modifier
                        .wrapContentHeight(CenterVertically)
                        .padding(start = 8.dp),
                    text = theMovies.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    softWrap = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier
                        .wrapContentHeight(CenterVertically)
                        .padding(start = 8.dp),
                    text = theMovies.releaseDate,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false
                )
            }
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
            voteCount = 0,
            userId = FirebaseAuth.getInstance().uid.toString()
        ),
        onItemClick = {}
    )
}