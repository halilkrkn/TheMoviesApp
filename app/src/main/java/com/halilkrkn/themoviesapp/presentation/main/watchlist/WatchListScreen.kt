package com.halilkrkn.themoviesapp.presentation.main.watchlist

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.halilkrkn.themoviesapp.core.Constants.TAG
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesInstance
import com.halilkrkn.themoviesapp.di.AppModule
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import com.halilkrkn.themoviesapp.presentation.main.components.SignOutPopup
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun WatchListScreen(
    navController: NavController,
) {

    var isProfilePopupVisible by remember { mutableStateOf(false) }
    val firebaseAuth: FirebaseAuth = Firebase.auth
    val firebaseUser = firebaseAuth.currentUser
    val scoper = rememberCoroutineScope()

    // TODO: 10.10.2021  Bu kısım silinecek
//    val serviceApi = AppModule.provideTheMoviesApi(AppModule.provideRetrofit(AppModule.provideHttpClient()))
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TopAppBar(
                    title = { Text(text = "WatchList") },
                    actions = {
                        IconButton(
                            onClick = {
                                isProfilePopupVisible = !isProfilePopupVisible
                            }
                        ) {
                            if (firebaseUser?.photoUrl != null) {
                                AsyncImage(
                                    model = firebaseUser.photoUrl,
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(36.dp)
                                        .border(
                                            border = BorderStroke(
                                                width = 1.dp,
                                                color = Color.DarkGray,
                                            ),
                                            shape = CircleShape
                                        )
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Profile Picture",
                                    tint = Color.Red
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Kullanıcı profili popup'ı
                if (isProfilePopupVisible) {
                    SignOutPopup(
                        firebaseUser = firebaseUser,
                        onSignOut = {
                            navController.popBackStack()
                            firebaseAuth.signOut()
                            navController.navigate(Graphs.AUTHENTICATION)
                        }
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // TODO: 10.10.2021  Bu kısım silinecek
//            GlobalScope.launch(Dispatchers.Main) {
//                    val photos = serviceApi.getPopularMovies(1, 123)
//                    Log.d(TAG, "The Movies: ${photos.theMoviesDtos.map {
//                        it.originalTitle
//                    }}")
//            }
        }
    }
}

