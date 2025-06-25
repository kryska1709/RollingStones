package com.example.rollingstones.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.components.FormView
import com.example.rollingstones.components.PasswordView
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.ui.theme.LightButtonColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.ui.theme.SecondColor
import com.example.rollingstones.viewmodel.AuthViewModel

@Composable
fun AuthView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isAdmin = authViewModel.isAdmin.collectAsState()
    val currentUser = authViewModel.currentUser.collectAsState()
    val enabled = remember { derivedStateOf { email.value.isNotEmpty() && password.value.isNotEmpty() } }
//    LaunchedEffect(Unit) {
//        authViewModel.getCurrentUser()
//    }
    val user = authViewModel.user.collectAsState()
    LaunchedEffect(user.value) {
        Log.i("suka dostalllloooo", user.value.toString())
        user.value?.let {
            if (it.isAdmin){
                navController.navigate(Screen.AdminHomeScreen.route)
            } else {
                navController.navigate(Screen.UserHomeScreen.route)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .imePadding()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Вход в аккаунт",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MainColor,
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FormView(email, "электронная почта", "ivanovivan11@gmail.com")
                    PasswordView(password, "пароль", "12345678")

                    Button(
                        onClick = {
                            if (enabled.value) {
                                authViewModel.auth(
                                    email.value,
                                    password.value
                                ) { success, errorMessage ->
                                    if (success) {
                                        Toast.makeText(context, "авторизация успешна", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "ошибка авторизации", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(context, "заполните все поля", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (enabled.value) DarkButtonColor else DarkButtonColor.copy(
                                alpha = 0.5f
                            )
                        )
                    ) {
                        Text("войти", color = Color.White, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(thickness = 1.dp, color = LightButtonColor.copy(0.5f))
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(
                            "Нет аккаунта?",
                            color = Color.Blue.copy(0.7f),
                            fontSize = 16.sp,
                            modifier = Modifier.align(
                                Alignment.CenterVertically
                            )
                        )
                        TextButton(onClick = { navController.navigate(Screen.RegScreen.route) }) {
                            Text(
                                "Зарегистрироваться",
                                color = SecondColor,
                                fontSize = 16.sp,
                                modifier = Modifier.align(
                                    Alignment.CenterVertically
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}