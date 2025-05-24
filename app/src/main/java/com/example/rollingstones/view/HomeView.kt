package com.example.rollingstones.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.R
import com.example.rollingstones.components.FormView
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BluDark
import com.example.rollingstones.ui.theme.BluLight
import com.example.rollingstones.ui.theme.MediumPurple
import com.example.rollingstones.ui.theme.MyPurple
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
    val firaSansFamily = FontFamily(
        Font(R.font.sketchpadnotebold),
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Rolling Stones",
                    fontFamily = firaSansFamily,
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Thin)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyPurple
                ),
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                enabled.value = !enabled.value
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.cancel_close_delete),
                                contentDescription = null,
                                tint = BluLight
                            )
                        }
                        IconButton(
                            onClick = {navController.navigate(Screen.UserSettingsScreen.route)}
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.settings_user_profile_icon_188630),
                                contentDescription = null,
                                tint = BluDark
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Добро пожаловать в боулинг-клуб Rolling Stones " +
                        "— идеальное место для отдыха и веселья! " +
                        "У нас вы сможете насладиться захватывающими играми в боулинг, уютной атмосферой и вкусной едой. " +
                        "\n" +
                        "Rolling Stones предлагает отличные дорожки для боулинга, удобные диваны для отдыха и бар с широким выбором напитков и закусок.\n",
                fontFamily = firaSansFamily,
                fontSize = 20.sp
            )
            Button(
                onClick = {enabledReserve.value = !enabledReserve.value}
            ) {
                Text(text = "забронировать дорожку")
            }
        }
    }
    if (enabled.value){
    AlertDialog(
        onDismissRequest = { enabled.value=false },
        containerColor = MediumPurple,
        confirmButton = {
            Button(
                onClick = {
                    enabled.value = !enabled.value
                    authViewModel.signOut()
                    navController.navigate(Screen.AuthScreen.route)
                    Toast.makeText(context,"Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(BluLight)
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
                colors = ButtonDefaults.buttonColors(BluLight)
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
                containerColor = MediumPurple,
                confirmButton = {
                    Button(
                        onClick = {
                            enabledReserve.value = !enabledReserve.value
                        },
                        colors = ButtonDefaults.buttonColors(BluLight)
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
                        colors = ButtonDefaults.buttonColors(BluLight)
                    ) {
                        Text(
                            text = "отмена"
                        )
                    }
                }
            )
        }
    }


