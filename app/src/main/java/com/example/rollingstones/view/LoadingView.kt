package com.example.rollingstones.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.viewmodel.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun LoadingView(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    val currentUser = authViewModel.currentUser.collectAsState()
    val userData = authViewModel.user.collectAsState()

    LaunchedEffect(currentUser.value) {
        delay(3000)
        if (currentUser.value != null) {
            try {
                authViewModel.getUser(currentUser.value!!)
                withContext(Dispatchers.Main) {
                    userData.value?.let { user ->
                        if (user.isAdmin == true) {
                            navController.navigate(Screen.AdminHomeScreen.route) {
                                popUpTo(Screen.LoadingScreen.route) { inclusive = true }
                            }
                        } else {
                            navController.navigate(Screen.UserHomeScreen.route) {
                                popUpTo(Screen.LoadingScreen.route) { inclusive = true }
                            }
                        }
                    } ?: run {
                        navController.navigate(Screen.AuthScreen.route) {
                            popUpTo(Screen.LoadingScreen.route) { inclusive = true }
                        }
                    }
                }
            } catch (error: Exception) {
                Log.e("LoadingView", "Error fetching user: ${error.message}")
                navController.navigate(Screen.AuthScreen.route) {
                    popUpTo(Screen.LoadingScreen.route) { inclusive = true }
                }
            }
        } else {
            navController.navigate(Screen.AuthScreen.route) {
                popUpTo(Screen.LoadingScreen.route) { inclusive = true }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("file:///android_asset/loading.gif") // путь к GIF-файлу
                .decoderFactory(coil.decode.GifDecoder.Factory())
                .build(),
            contentDescription = "Загрузка",
            modifier = Modifier.fillMaxSize()
        )
    }
}