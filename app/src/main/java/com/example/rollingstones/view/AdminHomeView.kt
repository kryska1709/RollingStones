package com.example.rollingstones.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.rollingstones.components.BookingItems
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.viewmodel.AdminViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeView(
    adminViewModel: AdminViewModel
) {
    val bookings = adminViewModel.booking.collectAsState()
    val isLoading = remember { mutableStateOf(true) }
    val refreshing = remember { mutableStateOf(false) }
    val stateRefresh = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        isLoading.value = true
        try{
            adminViewModel.loadAndCleanBookings()
            delay(2000)
            isLoading.value = false
        }catch (e: Exception){
            Log.e("adminHomeView died", e.message.toString())
        }
    }
    LaunchedEffect(Unit) {
        while (true){
            delay(300000)
            adminViewModel.loadAndCleanBookings()
        }
    }
    LaunchedEffect(refreshing.value) {
        if(refreshing.value){
            adminViewModel.allBookings()
            refreshing.value = false
        }
    }
    PullToRefreshBox(
        isRefreshing = refreshing.value,
        state = stateRefresh,
        onRefresh = {refreshing.value = true},
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = refreshing.value,
                state = stateRefresh,
                color = DarkButtonColor
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
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
            if (bookings.value.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackGround)
                ) {
                    Text(
                        text = "нет забронированных дорожек",
                        fontSize = 26.sp,
                        fontFamily = Bibliothy,
                        color = MainColor,
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
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
    }
}
