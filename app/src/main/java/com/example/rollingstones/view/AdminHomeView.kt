package com.example.rollingstones.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.rollingstones.components.BookingItems
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.viewmodel.AdminViewModel

@Composable
fun AdminHomeView(
    navController: NavController,
    adminViewModel: AdminViewModel
) {
    val bookings = adminViewModel.booking.collectAsState()
    val isDelete = adminViewModel.isDelete.collectAsState()
    val isLoading = remember{ mutableStateOf(false)}

    LaunchedEffect(Unit) {
        isLoading.value = true
        adminViewModel.allBookings()
        isLoading.value = false
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
    )  {
        if (isLoading.value) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else {
            items(bookings.value) {
                BookingItems(it)
            }
        }
    }
}