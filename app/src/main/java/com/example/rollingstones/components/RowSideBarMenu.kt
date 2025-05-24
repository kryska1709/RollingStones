package com.example.rollingstones.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.ui.theme.SecondColor
import com.example.rollingstones.ui.theme.ThirdColor
import java.nio.file.WatchEvent

@Composable
fun RowSideBarMenu(
    text: String,
    icon: Int,
    action: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { action() }
            .padding(top = 15.dp, bottom = 15.dp, start = 15.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = ThirdColor,
            modifier = Modifier.padding(12.dp)
        )
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = SecondColor
        )
    }
}