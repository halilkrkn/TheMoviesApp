package com.halilkrkn.themoviesapp.presentation.auth.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.halilkrkn.themoviesapp.ui.theme.lightBlue
import com.halilkrkn.themoviesapp.ui.theme.moreLightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    password: String,
    onPasswordValueChange: (newValue: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var passwordIsVisible by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = {newValue ->
            onPasswordValueChange(newValue)
        },
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = moreLightBlue,
            cursorColor = Color.Black,
            disabledLabelColor = lightBlue,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(color = Color.Black),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        visualTransformation = if (passwordIsVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        leadingIcon = {
           if (isSystemInDarkTheme()) {
               Icon(
                   Icons.Default.Password,
                   contentDescription = "Password",
                   tint = Color.Black
               )
           } else {
               Icon(
                   Icons.Default.Password,
                   contentDescription = "Password",
                   tint = Color.Black
               )
           }
        },
        trailingIcon = {
            val icon = if (passwordIsVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(
                onClick = {
                    passwordIsVisible = !passwordIsVisible
                }
            ) {
                if (isSystemInDarkTheme()) {
                    Icon(
                        icon,
                        contentDescription = "Password",
                        tint = Color.Black
                    )
                } else {
                    Icon(
                        icon,
                        contentDescription = "Password",
                        tint = Color.Black
                    )
                }
            }
        },
        placeholder = {
            if (isSystemInDarkTheme()) {
                Text(text = "Password", color = Color.Black)
            } else {
                Text(text = "Password", color = Color.Black)
            }
        }
    )
}