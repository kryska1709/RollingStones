package com.example.rollingstones.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rollingstones.ui.theme.DarkButtonColor

@Composable
fun RowSideBarMenu(
    text: String,
    icon: Int,
    color: Color,
    action: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { action() }
            .padding(top = 15.dp, bottom = 15.dp, start = 15.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(25.dp)
                .align(Alignment.CenterVertically),
            tint = color
        )
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkButtonColor,
            modifier = Modifier.padding(10.dp)
        )
    }
}