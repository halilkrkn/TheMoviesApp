package com.halilkrkn.themoviesapp.presentation.auth.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlineButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    loadingText: String ?= null,
    shape: Shape = CircleShape,
    borderColor: Color = Color.Green,
    clickBorderColor: Color = Color.Red,
    clickProgressBarColor: Color = Color.Yellow,

) {
    var clicked by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        border = BorderStroke(
            width = 2.dp,
            color = borderColor,
        ),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                color = Color.White,
                text = if (clicked) loadingText ?: text else text,
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (clicked) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    strokeWidth = 2.dp,
                    color = clickProgressBarColor
                )
            }
        }
    }

}