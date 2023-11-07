package com.halilkrkn.themoviesapp.presentation.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.themoviesapp.navigation.screens.AuthScreens
import com.halilkrkn.themoviesapp.navigation.util.Graphs.MAIN


@Composable
fun LoginScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                    navController.navigate(MAIN)
                },
            text = "Login",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .padding(top = 75.dp)
                .clickable {
                    navController.navigate(AuthScreens.Register.route)
                },
            text = "Register",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Medium
        )
    }
}