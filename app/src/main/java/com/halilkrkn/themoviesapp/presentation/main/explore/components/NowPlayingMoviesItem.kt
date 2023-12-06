package com.halilkrkn.themoviesapp.presentation.main.explore.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.core.Constants.IMAGE_BASE_URL
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar

@Composable
fun NowPlayingMoviesItem(
    theMovies: TheMovies,
    modifier: Modifier = Modifier,
    onItemClick: (TheMovies) -> Unit,
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
            .padding(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .clickable {
                    onItemClick(theMovies)
                }

        ) {
            SubcomposeAsyncImage(
                model = IMAGE_BASE_URL + theMovies.posterPath,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingProgressBar(
                            modifier = Modifier
                                .size(100.dp, 100.dp),
                            raw = R.raw.image_loading
                        )
                    }
                },
                error = {
                    LoadingProgressBar(
                        modifier = Modifier
                            .fillMaxHeight()
                            .size(100.dp, 100.dp),
                        raw = R.raw.image_error
                    )
                },
                contentDescription = theMovies.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = theMovies.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
            )
        }
    }

}

@Preview
@Composable
fun NowPlayingMoviesItemPreview() {
//    NowPlayingMoviesItem(
////        theMovies = ,
//        onItemClick = {}
//    )
//
}