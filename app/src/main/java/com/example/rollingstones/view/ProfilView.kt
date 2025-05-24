package com.example.rollingstones.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.R
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BluLight
import com.example.rollingstones.ui.theme.MyPurple
import com.example.rollingstones.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val firaSansFamily = FontFamily(
        Font(R.font.sketchpadnotebold),
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Профиль",
                        fontFamily = firaSansFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Thin,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(
                        onClick = {navController.navigate(Screen.UserHomeScreen.route)}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_112351),
                            contentDescription = null,
                            tint = BluLight
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyPurple
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Добро пожаловать в боулинг-клуб Rolling Stones " +
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
                fontFamily = firaSansFamily,
                fontSize = 20.sp
            )
        }
    }
}