package com.example.rollingstones.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rollingstones.components.FormView
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.FourthColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.ui.theme.ThirdColor
import com.example.rollingstones.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val enabled = remember { mutableStateOf(false) }
    val enabledReserve = remember { mutableStateOf(false) }
    val nameReserve = remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "главная." +
                    "забронируйте дорожку",
                color=MainColor)
        }
    }
    if (enabled.value){
    AlertDialog(
        onDismissRequest = { enabled.value=false },
        containerColor = BackGround,
        confirmButton = {
            Button(
                onClick = {
                    enabled.value = !enabled.value
                    authViewModel.signOut()
                    navController.navigate(Screen.AuthScreen.route)
                    Toast.makeText(context,"Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(FourthColor)
            ) {
                Text(
                    text = "выйти"
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    enabled.value = !enabled.value
                    authViewModel.deleteUser()
                    navController.navigate(Screen.AuthScreen.route)
                    Toast.makeText(context, "Аккаунт удалён", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(ThirdColor)
            ) {
                Text(
                    text = "удалить аккаунт"
                )
            }
        }
    )
    }
    if (enabledReserve.value){
            AlertDialog(
                text = {
                    Column {
                        Text(
                            text = "Для бронирования дорожки заполните все поля",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        FormView(nameReserve, "ваше имя", "Мария")
                    }
                },
                onDismissRequest = { enabled.value=false },
                containerColor = MainColor,
                confirmButton = {
                    Button(
                        onClick = {
                            enabledReserve.value = !enabledReserve.value
                        },
                        colors = ButtonDefaults.buttonColors(FourthColor)
                    ) {
                        Text(
                            text = "забронировать"
                        )
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            enabledReserve.value = !enabledReserve.value
                        },
                        colors = ButtonDefaults.buttonColors(FourthColor)
                    ) {
                        Text(
                            text = "отмена"
                        )
                    }
                }
            )
        }
    }


