package com.example.rollingstones.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.LightButtonColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.viewmodel.AdminViewModel

@Composable
fun BookingItems(
    bookingModel: BookingModel,
    adminViewModel: AdminViewModel
) {

    val context = LocalContext.current
    val enabled = remember {mutableStateOf(false)}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp), //скругление
        elevation = CardDefaults.cardElevation(6.dp) //тень под карточкой
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "дорожка: " + bookingModel.laneNumber.toString(),
                    color = MainColor,
                    fontSize = 22.sp,
                    fontFamily = Bibliothy
                )
                Text(
                    text = bookingModel.date,
                    color = MainColor,
                    fontFamily = Bibliothy
                )
                Text(
                    text = bookingModel.startTime + "-" + bookingModel.endTime,
                    color = MainColor,
                    fontFamily = Bibliothy
                )
                Text(
                    text = bookingModel.userEmail,
                    fontSize = 16.sp,
                    color = MainColor,
                    fontFamily = Bibliothy
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { enabled.value = true },
                    colors = ButtonDefaults.buttonColors(MainColor),
                    shape = ButtonDefaults.shape,
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    Text(
                        text = "отменить",
                        fontSize = 12.sp,
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }
        }
    }
    if(enabled.value){
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { enabled.value = false },
            title = { Text(text = "Подтверждение",
                color = MainColor) },
            text = { Text( text = "Вы уверены, что хотите отменить бронь?",
                color = MainColor.copy(1.7f),
                fontWeight = FontWeight.Bold) },
            confirmButton = {
                Button(
                    onClick = {
                        adminViewModel.deleteBookings(bookingModel.id)
                        enabled.value = false
                        Toast.makeText(context,"Бронирование успешно отменено", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(LightButtonColor)
                ) {
                    Text(text = "да",color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        enabled.value = false
                    },
                    colors = ButtonDefaults.buttonColors(LightButtonColor)
                ) {
                    Text(text = "нет",color = Color.White)
                }
            }
        )
    }
}
