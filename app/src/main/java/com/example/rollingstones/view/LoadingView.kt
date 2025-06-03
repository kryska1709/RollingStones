package com.example.rollingstones.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val currentUser = authViewModel.currentUser.collectAsState()
    LaunchedEffect(currentUser){
        delay(3000)
        if(currentUser.value!=null){
            try {
                authViewModel.getUser(currentUser.value!!)
                navController.navigate(Screen.UserHomeScreen.route)
            } catch (error: Exception){
                Log.e("loadingview error", error.message.toString())
                navController.navigate(Screen.AuthScreen.route)
            }
        } else {
            navController.navigate(Screen.AuthScreen.route)
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