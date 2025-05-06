package com.example.rollingstones.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rollingstones.components.CustomTextButton
import com.example.rollingstones.components.FormView
import com.example.rollingstones.components.PasswordView
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BluDark
import com.example.rollingstones.ui.theme.BluLight
import com.example.rollingstones.ui.theme.MyPurple


@Composable
fun AuthView(
    navController: NavController
) {
    val context = LocalContext.current
    val number = remember {mutableStateOf("")}
    val password = remember { mutableStateOf("") }
    val enabled = remember { derivedStateOf { number.value.isNotEmpty()&&password.value.isNotEmpty() } }
    Column(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormView(number,"номер телефона","+7(123)-456-78-90")
        PasswordView(password,"пароль","12345678")

        Button(
            onClick = {
                if (enabled.value) {
                    navController.navigate(Screen.UserHomeScreen.route)
                } else{
                    Toast.makeText(context,"заполните все поля", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if(enabled.value) BluDark else BluLight
            )
        ){
            Text(
                text = "войти"
            )
        }
        TextButton(
            onClick = {navController.navigate(Screen.RegScreen.route)}
        ) {
            Text(
                text = "Нет аккаунта? Зарегистрироваться",
                color = MyPurple
            )
        }
    }
}