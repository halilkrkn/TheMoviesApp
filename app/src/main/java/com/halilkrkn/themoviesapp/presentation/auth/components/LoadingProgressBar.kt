package com.halilkrkn.themoviesapp.presentation.auth.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingProgressBar(
    modifier: Modifier = Modifier,
    raw: Int
) {
    Box(
        modifier = modifier
            .size(100.dp,100.dp),
        contentAlignment = Alignment.Center,
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever,
            speed = 2f,
            alignment = Alignment.Center,
        )
    }
}