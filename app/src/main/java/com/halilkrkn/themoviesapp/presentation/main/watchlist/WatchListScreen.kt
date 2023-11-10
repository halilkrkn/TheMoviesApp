package com.halilkrkn.themoviesapp.presentation.main.watchlist

import android.annotation.SuppressLint
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.halilkrkn.themoviesapp.navigation.util.Graphs
import com.halilkrkn.themoviesapp.presentation.main.components.SignOutPopup
import com.halilkrkn.themoviesapp.presentation.main.watchlist.components.WatchListItemScreen
import kotlinx.coroutines.DelicateCoroutinesApi


@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun WatchListScreen(
    navController: NavController,
    viewModel: WatchListViewModel = hiltViewModel(),
) {
    val theMovies = viewModel.getAllTheMovies.collectAsLazyPagingItems()
    var isProfilePopupVisible by remember { mutableStateOf(false) }
    val firebaseAuth: FirebaseAuth = Firebase.auth
    val firebaseUser = firebaseAuth.currentUser

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
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
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WatchListItemScreen(
                theMovies = theMovies,
            )
        }
    }
}

