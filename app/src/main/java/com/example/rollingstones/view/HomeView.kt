package com.example.rollingstones.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.rollingstones.components.DatePickerDialog
import com.example.rollingstones.components.TimeSelectionGrid
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.viewmodel.AuthViewModel
import com.example.rollingstones.viewmodel.BookingViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    authViewModel: AuthViewModel,
    bookingViewModel: BookingViewModel
) {
    val context = LocalContext.current
    val isReserved = remember { mutableStateOf(false) }
    val laneNumber = remember { mutableStateOf(1) }
    val userEmail = authViewModel.currentUser.value!!.email
    val currentUser = authViewModel.currentUser.collectAsState()
    val scope = rememberCoroutineScope()
    val showDatePicker = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }
    val startSelectedTime = remember { mutableStateOf<String?>(null) }
    val endSelectedTime = remember { mutableStateOf<String?>(null) }
    val reserved = bookingViewModel.reserved.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .padding(16.dp)
    ) {
        Text(
            text = "Главная",
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

                TextButton(
                    onClick = {showDatePicker.value = true},
                ) {
                    Text(
                        text = "Выберите дату",
                        color = Color.Blue
                    )
                }

                Text(
                    text = "Выберите время",
                    color = Color.Blue
                )

                TimeSelectionGrid(startSelectedTime.value, endSelectedTime.value) { selectedTime ->
                    when{
                        startSelectedTime.value == null -> {
                            startSelectedTime.value = selectedTime
                            endSelectedTime.value = null
                        }
                        endSelectedTime.value == null ->{
                            if(startSelectedTime.value!! < selectedTime){
                                endSelectedTime.value = selectedTime
                            } else {
                                startSelectedTime.value = selectedTime
                                endSelectedTime.value = null
                            }
                        }
                        else -> {
                            startSelectedTime.value = selectedTime
                            endSelectedTime.value = null
                        }
                    }
                }

                if(showDatePicker.value){
                    DatePickerDialog(
                        onDateSelected =
                            {
                                selectedDate.value = it
                                startSelectedTime.value = null
                                endSelectedTime.value = null
                                showDatePicker.value = false
                            },
                        onDismiss = { showDatePicker.value = false }
                    )
                }

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

                Spacer(modifier = Modifier.height(10.dp))
                selectedDate.value?.let {
                    Text(
                        text = it.toString(),
                        fontFamily = Bibliothy,
                        color = Color.Blue
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                if(startSelectedTime.value != null && endSelectedTime.value!=null) {
                    Text(
                        text = startSelectedTime.value.toString() + "-" + endSelectedTime.value.toString(),
                        fontFamily = Bibliothy,
                        color = Color.Blue
                    )
                }
                // Кнопка "Забронировать"
                Button(
                    onClick = {
                        if (!startSelectedTime.value.isNullOrEmpty() && !endSelectedTime.value.isNullOrEmpty() && !selectedDate.value.toString().isNullOrEmpty()) {
                            scope.launch {
                                bookingViewModel.isLaneReserved(
                                    selectedDate.value.toString(),
                                    startSelectedTime.value.toString(),
                                    endSelectedTime.value.toString(),
                                    laneNumber.value
                                )
                                if (reserved.value) {
                                    currentUser.value?.let {
                                        bookingViewModel.createBooking(
                                            selectedDate.value.toString(),
                                            startSelectedTime.value.toString(),
                                            endSelectedTime.value.toString(),
                                            laneNumber.value,
                                            userEmail.toString(),
                                            it
                                        )
                                    }
                                    isReserved.value = true
                                    Toast.makeText(
                                        context,
                                        "Бронь успешно создана на ${selectedDate.value} c ${startSelectedTime.value} до ${endSelectedTime.value}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "дорожка на это время уже забронирована",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    Toast.makeText(
                                        context,
                                        "выберите другую дорожку",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Пожалуйста, выберите дату и время",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    enabled = !startSelectedTime.value.isNullOrEmpty() &&  !endSelectedTime.value.isNullOrEmpty() && !selectedDate.value.toString().isNullOrEmpty(),
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
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}
