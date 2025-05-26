package com.example.rollingstones.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authoriz = authViewModel.authoriz.collectAsState()
    LaunchedEffect(authoriz){
        if(authoriz.value){
            navController.navigate(Screen.UserHomeScreen.route)
        } else {
            delay(500)
            navController.navigate(Screen.AuthScreen.route)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(color = DarkButtonColor)

    }
}