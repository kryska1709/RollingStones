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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.components.FormView
import com.example.rollingstones.components.PasswordView
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.*
import com.example.rollingstones.viewmodel.AuthViewModel

@Composable
fun RegView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val number = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val enabled = remember {
        derivedStateOf {
            name.value.isNotEmpty() &&
                    email.value.isNotEmpty() &&
                    number.value.isNotEmpty() &&
                    password.value.isNotEmpty() &&
                    confirmPassword.value.isNotEmpty()
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
                text = "Регистрация",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MainColor,
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FormView(name, "ваше имя", "Иван")
                    FormView(email, "электронная почта", "ivanovivan11@gmail.com")
                    FormView(number, "номер телефона", "1 234 567 89 01")
                    PasswordView(password, "пароль", "12345678")
                    PasswordView(confirmPassword, "подтверждение пароля", "12345678")

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            when {
                                !enabled.value -> {
                                    Toast.makeText(context, "заполните все поля", Toast.LENGTH_SHORT).show()
                                }
                                password.value != confirmPassword.value -> {
                                    Toast.makeText(context, "ваши пароли не совпадают", Toast.LENGTH_SHORT).show()
                                }
                                password.value.length < 8 -> {
                                    Toast.makeText(context, "пароль слишком короткий", Toast.LENGTH_SHORT).show()
                                }
                                else -> {
                                    authViewModel.reg(email.value, password.value) { success, _ ->
                                        if (success) {
                                            authViewModel.createUser(email.value, number.value, name.value)
                                            navController.navigate(Screen.AuthScreen.route)
                                            Toast.makeText(context, "регистрация успешна", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(context, "пользователь не зарегистрирован", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (enabled.value) DarkButtonColor else DarkButtonColor.copy(alpha = 0.5f)
                        )
                    ) {
                        Text("зарегистрироваться", color = Color.White, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(thickness = 1.dp, color = LightButtonColor.copy(0.5f))
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(
                            "Есть аккаунт?",
                            color = Color.Blue.copy(0.7f),
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        TextButton(onClick = { navController.navigate(Screen.AuthScreen.route) }) {
                            Text(
                                "Авторизироваться",
                                color = SecondColor,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}
