package com.example.rollingstones.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.MainColor

@Composable
fun InfoView(
    navController: NavController
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Text(
            text = "\n" +
                    "Добро пожаловать в боулинг-клуб Rolling Stones " +
                    "— идеальное место для отдыха и веселья! " +
                    "У нас вы сможете насладиться захватывающими играми в боулинг, уютной атмосферой и вкусной едой. " +
                    "\n" +
                    "Rolling Stones предлагает отличные дорожки для боулинга, удобные диваны для отдыха и бар с широким выбором напитков и закусок.\n" +
                    "\n" +
                    "Адрес:\n" +
                    "г. Пенза, ул. Суворова, д. 44\n" +
                    "\n" +
                    "Телефон:\n" +
                    "+7 (937) 916-76-31\n" +
                    "\n" +
                    "Часы работы:\n" +
                    "Пн-Чт: 10:00 - 23:00  \n" +
                    "Пт-Вс: 10:00 - 02:00\n" +
                    "\n" +
                    "Приходите в Rolling Stones, чтобы провести время с друзьями или семьей, " +
                    "поучаствовать в турнирах или просто расслабиться с игрой в боулинг. " +
                    "" +
                    "Мы гарантируем вам море положительных эмоций и незабываемые впечатления!",
            fontSize = 20.sp,
            color = MainColor
        )
    }
}