package com.example.rollingstones.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.LightButtonColor
import com.example.rollingstones.ui.theme.MainColor

@Composable
fun InfoView(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "О нас",
            fontSize = 28.sp,
            color = MainColor,
            fontWeight = FontWeight.Bold,
            fontFamily = Bibliothy,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp), //скругление
            elevation = CardDefaults.cardElevation(6.dp) //тень под карточкой
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Добро пожаловать в боулинг-клуб Rolling Stones — идеальное место для отдыха и веселья!",
                    fontSize = 18.sp,
                    fontFamily = Bibliothy,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "🎳 Что вас ждет:",
                    fontSize = 20.sp,
                    color = MainColor,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "• Захватывающие игры в боулинг\n" +
                            "• Уютная атмосфера\n" +
                            "• Вкусная еда и напитки\n" +
                            "• Удобные диваны и современное оборудование",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "📍 Адрес:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor
                )
                Text(
                    text = "г. Пенза, ул. Суворова, д. 44",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontFamily = Bibliothy
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "📞 Телефон:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor
                )
                Text(
                    text = "+7 (937) 916-76-31",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontFamily = Bibliothy
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "🕒 Часы работы:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor
                )
                Text(
                    text = "Пн–Чт: 10:00 – 23:00\nПт–Вс: 10:00 – 02:00",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontFamily = Bibliothy
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Приходите в Rolling Stones, чтобы провести время с друзьями или семьей, поучаствовать в турнирах или просто расслабиться. Мы гарантируем вам море положительных эмоций и незабываемые впечатления!",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontFamily = Bibliothy
                )
            }
        }
    }
}
