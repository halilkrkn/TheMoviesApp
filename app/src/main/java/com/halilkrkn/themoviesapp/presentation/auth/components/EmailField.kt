package com.halilkrkn.themoviesapp.presentation.auth.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.halilkrkn.themoviesapp.ui.theme.lightBlue
import com.halilkrkn.themoviesapp.ui.theme.moreLightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    email: String,
    onEmailValueChange: (newValue: String) -> Unit
) {
    TextField(
        value = email,
        onValueChange = { newValue ->
            onEmailValueChange(newValue)
        },
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        textStyle = TextStyle(color = Color.Black),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = moreLightBlue,
            cursorColor = Color.Black,
            disabledLabelColor = lightBlue,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            if (isSystemInDarkTheme()) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email",
                    tint = Color.Black
                )
            } else {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email",
                    tint = Color.Black
                )
            }
        },
        singleLine = true,
        placeholder = {
            if (isSystemInDarkTheme()) {
                Text(text = "Email", color = Color.Black)
            } else {
                Text(text = "Email", color = Color.Black)
            }
        }
    )
}