package com.example.rollingstones.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rollingstones.model.HistoryReservedModel

@Composable
fun HistoryItem(
    historyReservedModel: HistoryReservedModel
){
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(historyReservedModel.date, color = Color.Blue)
        Spacer(modifier = Modifier.width(3.dp))
        Text(historyReservedModel.startTime + "-" + historyReservedModel.endTime, color = Color.Blue)
        Spacer(modifier = Modifier.width(3.dp))
        Text("Дорожка №${historyReservedModel.laneNumber}", color = Color.Blue)
    }
}
