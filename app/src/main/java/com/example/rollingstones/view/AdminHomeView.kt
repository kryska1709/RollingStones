package com.example.rollingstones.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.rollingstones.components.BookingItems
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.viewmodel.AdminViewModel
import kotlinx.coroutines.delay

@Composable
fun AdminHomeView(
    navController: NavController,
    adminViewModel: AdminViewModel
) {
    val bookings = adminViewModel.booking.collectAsState()
    val isDelete = adminViewModel.isDelete.collectAsState()
    val isLoading = remember { mutableStateOf(true) }

    // Показываем гифку загрузки 3 секунды
    LaunchedEffect(Unit) {
        adminViewModel.allBookings()
        delay(3000)
        isLoading.value = false
    }

    if (isLoading.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("file:///android_asset/loading.gif")
                    .decoderFactory(GifDecoder.Factory())
                    .build(),
                contentDescription = "Загрузка",
                modifier = Modifier.fillMaxSize()
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackGround)
        ) {
            items(bookings.value) {
                BookingItems(it, adminViewModel)
            }
        }
    }
}
