package com.example.rollingstones.view

import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.components.FormView
import com.example.rollingstones.components.PasswordView
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.FourthColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.ui.theme.ThirdColor
import com.example.rollingstones.viewmodel.AuthViewModel

@Composable
fun RegView(
    navController: NavController,
    authViewModel: AuthViewModel,
    ){
    val context = LocalContext.current
    val email = remember {mutableStateOf("")}
    val name = remember { mutableStateOf("") }
    val number = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("")}
    val confirmPassword = remember { mutableStateOf("") }
    val enabled = remember { derivedStateOf { name.value.isNotEmpty()&&email.value.isNotEmpty()&&password.value.isNotEmpty()&&confirmPassword.value.isNotEmpty()&&number.value.isNotEmpty() } }
    Column(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormView(name,"ваше имя","Иван")
        FormView(email,"электронная почта","ivanovivan11@gmail.com")
        FormView(number,"номер телефона","1 234 567 89 01")
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
                    authViewModel.reg(email.value,password.value){success,errorMessage ->
                        if(success) {
                            navController.navigate(Screen.AuthScreen.route)
                            Toast.makeText(context,"регистрация успешна", Toast.LENGTH_SHORT).show()
                            authViewModel.createUser(email.value,number.value,name.value)
                        }
                        else {
                            Toast.makeText(context, "пользователь не зарегистрирован", Toast.LENGTH_SHORT).show()
                            Log.e("registration error",errorMessage.toString())
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if(enabled.value) ThirdColor else ThirdColor.copy(alpha = 1.5f)
            )
        ){
            Text(
                text = "зарегистрироваться",
                color = Color.White,
                fontSize = 16.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "Есть аккаунт?",
                color = FourthColor,
                fontSize = 16.sp
            )
            TextButton(
                onClick = { navController.navigate(Screen.AuthScreen.route) }
            ) {
                Text(
                    text = "Авторизироваться",
                    color = MainColor,
                    fontSize = 16.sp
                )
            }
        }
    }
}