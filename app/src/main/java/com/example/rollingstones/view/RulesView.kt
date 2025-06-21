package com.example.rollingstones.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.MainColor

@Composable
fun RulesView(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(BackGround)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "\uD83D\uDCDD Правила для администраторов боулинг-клуба RollingStones",
            fontSize = 28.sp,
            fontFamily = Bibliothy,
            color = MainColor,
            modifier = Modifier.padding(bottom = 12.dp),
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

        }
    }
}