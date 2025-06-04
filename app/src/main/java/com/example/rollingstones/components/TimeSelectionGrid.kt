package com.example.rollingstones.components

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@SuppressLint("DefaultLocale")
@Composable
fun TimeSelectionGrid(
    selectedTime: String?,
    onTimeSelected: (String) -> Unit
) {
    val timeSlots = remember { (10..23).map { hour -> String.format("%02d:00", hour) } }
    LazyVerticalGrid(
        columns = GridCells.Fixed(5)
    ) {
        items(timeSlots) {
            TimeSlotItem(it, it == selectedTime) {
                onTimeSelected(it)
            }
        }
    }
}