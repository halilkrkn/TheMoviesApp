package com.halilkrkn.themoviesapp.presentation.auth.signin

import android.os.Build.VERSION.SDK_INT
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.core.Constants.SIGNINING_UP_BUTTON
import com.halilkrkn.themoviesapp.core.Constants.SIGN_IN_BUTTON
import com.halilkrkn.themoviesapp.core.Constants.SIGN_UP_BUTTON
import com.halilkrkn.themoviesapp.navigation.screens.AuthScreens
import com.halilkrkn.themoviesapp.navigation.util.Graphs.MAIN
import com.halilkrkn.themoviesapp.presentation.auth.components.CustomOutlineButton
import com.halilkrkn.themoviesapp.presentation.auth.components.EmailField
import com.halilkrkn.themoviesapp.presentation.auth.components.PasswordField
import com.halilkrkn.themoviesapp.ui.theme.LightGray
import com.halilkrkn.themoviesapp.ui.theme.orangeColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val keyboard = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState
    val firebaseAuth: FirebaseAuth = Firebase.auth

    val googleSignInState = viewModel.googleState
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                scope.launch {
                    viewModel.googleSignIn(credentials)
                }
            } catch (it: ApiException) {
                print(it)
            }
        }

    LaunchedEffect(key1 = googleSignInState.value.success, key2 = state.value.isSuccess) {
        if (firebaseAuth.currentUser != null) {
            navController.popBackStack()
            navController.navigate(MAIN)
        }
    }

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        if (googleSignInState.value.loading || state.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 20.dp),
                color = orangeColor,
                strokeWidth = 2.dp,
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            contentScale = ContentScale.FillHeight,
            painter = rememberAsyncImagePainter
                (
                model = R.raw.movies_poster,
                imageLoader = imageLoader,
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.lighting(
                multiply = LightGray,
                add = Color.Black
            )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter your credential's to register",
                modifier = Modifier.padding(bottom = 24.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Color.Gray,
                fontFamily = FontFamily.Default
            )
            EmailField(
                email = email,
                onEmailValueChange = {
                    email = it
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(
                password = password,
                onPasswordValueChange = {
                    password = it
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            CustomOutlineButton(
                onClick = {
                    scope.launch {
                        keyboard?.hide()
                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Please fill all the fields",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            return@launch
                        }
                        navController.popBackStack()
                        viewModel.signInWithEmailAndPassword(email, password)
                    }
                },
                borderColor = orangeColor,
                text = SIGN_IN_BUTTON,
                loadingText = SIGNINING_UP_BUTTON,
                clickProgressBarColor = Color.Red
            )

            CustomOutlineButton(
                onClick = {
                    navController.navigate(AuthScreens.SignUp.route)
                },
                borderColor = orangeColor,
                text = SIGN_UP_BUTTON
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "or connect with", fontWeight = FontWeight.Medium, color = Color.Gray)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken(context.getString(R.string.web_client_id))
                        .build()

                    val googleSingInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSingInClient.signInIntent)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Icon",
                        modifier = Modifier.size(50.dp),
                        tint = Color.Unspecified
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook Icon",
                        modifier = Modifier.size(50.dp),
                        tint = Color.Unspecified
                    )
                }
                LaunchedEffect(key1 = state.value.isSuccess) {
                    scope.launch {
                        if (state.value.isSuccess?.isNotEmpty() == true) {
                            val success = state.value.isSuccess
                            Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                            navController.popBackStack()
                            navController.navigate(MAIN)
                        }
                    }
                }

                LaunchedEffect(key1 = state.value.isError) {
                    scope.launch {
                        if (state.value.isError?.isNotEmpty() == true) {
                            val error = state.value.isError
                            Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                LaunchedEffect(key1 = googleSignInState.value.success) {
                    scope.launch {
                        if (googleSignInState.value.success != null) {
                            Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()

                            navController.popBackStack()
                            navController.navigate(MAIN)
                            viewModel.resetSignInState()
                        }
                    }
                }

            }
        }
    }
}
