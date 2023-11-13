package com.halilkrkn.themoviesapp.presentation.main.favorites

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.di.AppModule
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FavoritesScreen(navController: NavController) {

    val apiService = AppModule.provideTheMoviesApi(AppModule.provideRetrofit(AppModule.provideHttpClient()))
    val scope = rememberCoroutineScope()
    scope.launch {
        val response = apiService.searchTheMovies("Wall Street")
        Log.d("FavoritesScreen", "response: $response")
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .clickable {
                    navController.navigate(Graphs.DETAILS)
                },
            text = "Favorites Screen",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}