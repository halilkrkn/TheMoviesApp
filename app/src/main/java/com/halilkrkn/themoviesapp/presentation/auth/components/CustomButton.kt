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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    loadingText: String,
    iconContentDescription: String ?= null,
    shape: Shape = CircleShape,
    borderColor: Color = Color.Green,
    clickBorderColor: Color = Color.Red,
    clickProgressBarColor: Color = Color.Yellow,
    buttonColors: ButtonColors,
    icon: Painter ?= null,
) {

    var clicked by remember { mutableStateOf(false) }

    // Google ile giriş yapma düğmesi
    Button(
        onClick = {
            // Google ile giriş işlemleri yapılabilir.
            clicked = !clicked
            onClick()
        },
        shape = shape,
        border = BorderStroke(
            width = 2.dp,
            color = if (clicked) borderColor else clickBorderColor,
        ),
        colors = buttonColors
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
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
                text = if (clicked) loadingText else text
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