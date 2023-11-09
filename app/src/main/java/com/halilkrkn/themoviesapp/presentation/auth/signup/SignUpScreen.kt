package com.halilkrkn.themoviesapp.presentation.auth.signup

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.core.Constants
import com.halilkrkn.themoviesapp.navigation.screens.AuthScreens
import com.halilkrkn.themoviesapp.navigation.util.Graphs.MAIN
import com.halilkrkn.themoviesapp.presentation.auth.components.CustomOutlineButton
import com.halilkrkn.themoviesapp.presentation.auth.components.EmailField
import com.halilkrkn.themoviesapp.presentation.auth.components.LoadingProgressBar
import com.halilkrkn.themoviesapp.presentation.auth.components.PasswordField
import com.halilkrkn.themoviesapp.ui.theme.LightGray
import com.halilkrkn.themoviesapp.ui.theme.orangeColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    navController: NavController,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val keyboard = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: SignUpViewModel = hiltViewModel()
    val state = viewModel.signUpState

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            contentScale = androidx.compose.ui.layout.ContentScale.FillHeight,
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
                modifier = Modifier.padding(bottom = 10.dp),
                text = "Create Account",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 35.sp,
                fontFamily = FontFamily.Default,
            )
            Text(
                text = "Enter your credential's to register",
                modifier = Modifier.padding(bottom = 30.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Color.Gray,
                fontFamily = FontFamily.Default,
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
            CustomOutlineButton(
                onClick = {
                    scope.launch {
                        keyboard?.hide()
                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG)
                                .show()
                            return@launch
                        }
                        viewModel.signUpWithEmailAndPassword(email, password)
                        navController.popBackStack()
                        navController.navigate(MAIN)
                    }
                },
                borderColor = orangeColor,
                text = Constants.SIGN_UP_BUTTON,
                loadingText = Constants.SIGNINING_UP_BUTTON,
                clickProgressBarColor = Color.Red
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (state.value.isLoading) {
                    LoadingProgressBar(raw = R.raw.movie_splash_1)
                }
            }
            Text(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        navController.popBackStack()
                        navController.navigate(AuthScreens.SignIn.route)
                    },
                text = "Already Have an account? sign In",
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontFamily = FontFamily.Default
            )
        }
    }
}