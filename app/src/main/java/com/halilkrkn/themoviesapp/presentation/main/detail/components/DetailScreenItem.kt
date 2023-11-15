package com.halilkrkn.themoviesapp.presentation.main.detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.halilkrkn.themoviesapp.core.Constants.IMAGE_BASE_URL
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.ui.theme.StarColor
import com.halilkrkn.themoviesapp.ui.theme.TheMoviesAppTheme
import com.halilkrkn.themoviesapp.ui.theme.moreLightBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                model = IMAGE_BASE_URL + theMovies.backdropPath,
                contentDescription = theMovies.title,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .fillMaxHeight()
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
                color = White
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = theMovies.releaseDate,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = White,
                    textDirection = TextDirection.Rtl
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
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = theMovies.overview,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = Black,
                textDirection = TextDirection.ContentOrLtr

            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenItemPreview() {
    TheMoviesAppTheme {
        DetailScreenItem(
            theMovies = TheMovies(
                id = 1,
                adult = false,
                backdropPath = "",
                originalLanguage = "",
                originalTitle = "Hızlı ve Öfkeli 9",
                overview = "Görevimiz Tehlike: Ölümcül Hesaplaşma Bölüm 1'de, Ethan Hunt (Tom Cruise) ve IMF ekibi şimdiye kadarki en tehlikeli görevlerine atılıyorlar. Görevleri; yanlış ellere geçmeden önce tüm insanlığı tehdit eden  korkunç bir silahın izini sürmek. Geleceğin kontrolü ve dünyanın kaderi tehlikedeyken ve Ethan'ın geçmişinden gelen karanlık güçler yaklaşırken, dünya çapında ölümcül bir yarış başlıyor. Gizemli, her şeye gücü yeten bir düşmanla karşı karşıya kalan Ethan, hiçbir şeyin görevinden daha önemli olamayacağını düşünmek zorunda kalır. En çok değer verdiği kişilerin hayatları bile.",
                popularity = 0.0,
                posterPath = "https://image.tmdb.org/t/p/w500//cHkhb5A4gQRK6zs6Pv7zorHs8Nk.jpg",
                releaseDate = "2023-07-08",
                title = "Hızlı ve Öfkeli 9",
                video = false,
                voteAverage = 4.5,
                voteCount = 0
            ),
        )
    }
}
