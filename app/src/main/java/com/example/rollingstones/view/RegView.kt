package com.example.rollingstones.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rollingstones.components.FormView
import com.example.rollingstones.components.PasswordView
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BluDark
import com.example.rollingstones.ui.theme.BluLight

@Composable
fun RegView(
    navController: NavController
){
    val context = LocalContext.current
    val number = remember {mutableStateOf("")}
    val name = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("")}
    val confirmPassword = remember { mutableStateOf("") }
    val enabled = remember { derivedStateOf { name.value.isNotEmpty()&&number.value.isNotEmpty()&&password.value.isNotEmpty()&&confirmPassword.value.isNotEmpty() } }
    Column(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormView(name,"ваше имя","Иван")
        FormView(number,"номер телефона","+7(123)-456-78-90")
        PasswordView(password,"пароль","12345678")
        PasswordView(confirmPassword,"подтверждение пароля","12345678")

        Button(
            onClick = {
                if(!enabled.value){
                    Toast.makeText(context,"заполните все поля", Toast.LENGTH_SHORT).show()
                }
                else if (password.value != confirmPassword.value) {
                    Toast.makeText(context, "ваши пароли не совпадают", Toast.LENGTH_SHORT).show()
                } else if (password.value.length < 8) {
                    Toast.makeText(context, "пароль слишком короткий", Toast.LENGTH_SHORT).show()
                } else {
                    navController.navigate(Screen.AuthScreen.route)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if(enabled.value) BluDark else BluLight
            )
        ){
            Text(
                text = "зарегистрироваться"
            )
        }
    }
}