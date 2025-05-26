package com.example.rollingstones.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.example.rollingstones.ui.theme.LightButtonColor
import com.example.rollingstones.ui.theme.SecondColor
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.viewmodel.AuthViewModel

@Composable
fun AuthView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val enabled = remember { derivedStateOf { email.value.isNotEmpty() && password.value.isNotEmpty() } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormView(email, "электронная почта", "ivanovivan11@gmail.com")
        PasswordView(password, "пароль", "12345678")

        Button(
            onClick = {
                if (enabled.value) {
                    authViewModel.auth(email.value, password.value) { success, errorMessage ->
                        if (success) {
                            navController.navigate(Screen.UserHomeScreen.route)
                            Toast.makeText(context, "авторизация успешна", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "ошибка авторизации", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "заполните все поля", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enabled.value) DarkButtonColor else DarkButtonColor.copy(alpha = 0.5f)
            ),
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text("войти", color = Color.White, fontSize = 16.sp)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Нет аккаунта?", color = Color.Blue, fontSize = 16.sp, modifier = Modifier.align(
                Alignment.CenterVertically), fontWeight = FontWeight.Bold)
            TextButton(onClick = { navController.navigate(Screen.RegScreen.route) }) {
                Text("Зарегистрироваться", color = SecondColor, fontSize = 16.sp, modifier = Modifier.align(
                    Alignment.CenterVertically),fontWeight = FontWeight.Bold)
            }
        }
    }
}
