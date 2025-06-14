package com.example.rollingstones.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.MainColor

@Composable
fun BookingItems(
    bookingModel: BookingModel
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp), //скругление
        elevation = CardDefaults.cardElevation(6.dp) //тень под карточкой
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // немного отступа
        ) {
            Text(
                text = bookingModel.date,
                color = MainColor,
                fontFamily = Bibliothy
            )
            Text(
                text = bookingModel.startTime,
                color = MainColor,
                fontFamily = Bibliothy
            )
            Text(
                text = bookingModel.endTime,
                color = MainColor,
                fontFamily = Bibliothy
            )
            Text(
                text = bookingModel.laneNumber.toString(),
                color = MainColor,
                fontFamily = Bibliothy
            )
        }
    }
}