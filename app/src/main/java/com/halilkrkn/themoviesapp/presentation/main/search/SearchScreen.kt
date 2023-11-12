package com.halilkrkn.themoviesapp.presentation.main.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.core.Constants.TAG
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesInstance
import com.halilkrkn.themoviesapp.di.AppModule
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(navController: NavController) {

//    val apiService = TheMoviesInstance.apiService
//    val api = AppModule.provideTheMoviesApi(AppModule.provideRetrofit(AppModule.provideHttpClient()))
//    var scope = rememberCoroutineScope()
//
//    LaunchedEffect(key1 = null){
//        scope.launch {
//            kotlin.runCatching {
//                Log.d(TAG, "SearchScreen: ${api.getTheMoviesDetails(74)}")
//            }.onFailure {
//                Log.d(TAG, "SearchScreen: ${it.message}")
//            }
//        }
//    }


//    Box(
//        modifier = Modifier
//            .fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            modifier = Modifier,
//            text = "${apiService.getTheMoviesDetails(74)}",
//            color = MaterialTheme.colorScheme.primary,
//            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
//            fontWeight = FontWeight.Bold
//        )
//    }
}