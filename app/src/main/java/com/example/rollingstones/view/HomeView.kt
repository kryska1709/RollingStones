package com.example.rollingstones.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.R
import com.example.rollingstones.components.DateTextField
import com.example.rollingstones.components.TimeTextField
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.ui.theme.LightButtonColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.viewmodel.AuthViewModel
import com.example.rollingstones.viewmodel.BookingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    authViewModel: AuthViewModel,
    bookingViewModel: BookingViewModel
) {
    val context = LocalContext.current
    val date = remember { mutableStateOf("") }
    val time = remember { mutableStateOf("") }
    val isReserved = remember { mutableStateOf(false) }
    val laneNumber = remember { mutableStateOf(1) }
    val currentUser = authViewModel.currentUser.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .padding(16.dp)
    ) {
        Text(
            text = "главная",
            color = MainColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Bibliothy,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Забронируйте дорожку",
                    color = MainColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Ввод даты и времени
                DateTextField(date.value) { date.value = it }
                TimeTextField(time.value) { time.value = it }
                Row(
                ){
                    Text(
                        text = "Дорожка: ",
                        color = Color.Blue,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 18.sp
                    )
                    IconButton(
                        onClick = {
                            if (laneNumber.value > 1){
                                laneNumber.value--
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable._726084_minus_icon),
                            tint = DarkButtonColor,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = laneNumber.value.toString(),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = MainColor,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    IconButton(
                        onClick = {
                            if (laneNumber.value < 5){
                                laneNumber.value++
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable._726331_plus_icon),
                            tint = DarkButtonColor,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка "Забронировать"
                Button(
                    onClick = {
                        if (date.value.isNotEmpty() && time.value.isNotEmpty()) {
                            scope.launch {
                                currentUser.value?.let {
                                    bookingViewModel.createBooking(
                                        date.value, time.value, laneNumber.value, it)
                                }
                            }
                            isReserved.value = true
                            Toast.makeText(
                                context,
                                "Бронь успешно создана на ${date.value} в ${time.value}",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Пожалуйста, выберите дату и время",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    enabled = date.value.isNotEmpty() && time.value.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkButtonColor,
                        contentColor = Color.White,
                        disabledContainerColor = DarkButtonColor.copy(0.4f),
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(
                        "Забронировать",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Отображение сообщения о брони
                if (isReserved.value) {
                    Text(
                        text = "Вы забронировали дорожку номер ${laneNumber.value} на ${date.value} в ${time.value} ",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
