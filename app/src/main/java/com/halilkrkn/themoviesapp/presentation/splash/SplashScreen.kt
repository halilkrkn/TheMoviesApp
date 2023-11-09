package com.halilkrkn.themoviesapp.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.halilkrkn.themoviesapp.R
import com.halilkrkn.themoviesapp.navigation.util.Graphs.AUTHENTICATION
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 5000
        ), label = "The Movies App Splash Screen Animation"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(AUTHENTICATION)
    }

    SplashScreenSetup(alpha = alphaAnim.value)
}

@Composable
fun SplashScreenSetup(alpha: Float) {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.movie_splash)
    )

    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.DarkGray)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LottieAnimation(
            modifier = Modifier.size(size = 360.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}
