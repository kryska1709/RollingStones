package com.example.rollingstones.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.MainColor

@Composable
fun TimeSlotItem(
    time: String,
    select: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
          .padding(4.dp)
          .aspectRatio(1f)
          .clip(RoundedCornerShape(5.dp))
          .background(if (select) MainColor else MainColor.copy(0.5f))
          .clickable{onClick()},
        contentAlignment = Alignment.Center
    ){
        Text(
            text = time,
            color = Color.White,
            fontFamily = Bibliothy
        )
    }
}