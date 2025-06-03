package com.example.rollingstones.view

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.rollingstones.components.HistoryItem
import com.example.rollingstones.components.TextFieldProfile
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val isEditing = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val enabled = remember { mutableStateOf(false) }
    val user = authViewModel.user.collectAsState()//отслеживать изменения метод
    val refreshing = remember { mutableStateOf(false) }
    val stateRefresh = rememberPullToRefreshState()
    val currentUser = authViewModel.currentUser.collectAsState()
    val isExpanded = remember { mutableStateOf(false) }
    LaunchedEffect(refreshing.value) {
        currentUser.value?.let { authViewModel.getUser(it) }
    }
    PullToRefreshBox(
        isRefreshing = refreshing.value,
        state = stateRefresh,
        onRefresh = { refreshing.value = true }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackGround)
                .padding(16.dp)
        ) {
            Text(
                text = "Ваш профиль",
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
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextFieldProfile(user.value?.name ?: "нет данных", "Имя", isEditing)
                    TextFieldProfile(
                        user.value?.email ?: "нет данных",
                        "Электронная почта",
                        isEditing
                    )
                    TextFieldProfile(user.value?.number ?: "нет данных", "Телефон", isEditing)

                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "история бронирования: ",
                                color = MainColor,
                                modifier = Modifier.padding(top = 7.dp)
                            )
                            IconButton(
                                onClick = { isExpanded.value = !isExpanded.value },
                                modifier = Modifier.padding(start = 70.dp)
                            ){
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_down_up_svgrepo_com),
                                    contentDescription = null,
                                    tint = Color.Blue,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        }

                        AnimatedVisibility(
                            visible = isExpanded.value,
                            enter = expandVertically(),
                            exit = shrinkVertically()
                        ) {
                            LazyColumn {
                                user.value?.historyReserve?.let { list ->
                                    items(list) { item ->
                                        HistoryItem(item)
                                    }
                                }
                            }
                        }
                    }
                    Button(
                        onClick = { isEditing.value = !isEditing.value },
                        colors = ButtonDefaults.buttonColors(DarkButtonColor)
                    ) {
                        Text(
                            if (isEditing.value) "Сохранить" else "Изменить",
                            color = Color.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Text(
                    text = "если вы хотите удалить аккаунт",
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    color = MainColor
                )
                Text(
                    text = "\uD83D\uDC47",
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    fontSize = 18.sp
                )
                Button(
                    onClick = {
                        enabled.value = !enabled.value
                        user.value?.let { authViewModel.deleteUser(it.email) }
                        navController.navigate(Screen.AuthScreen.route)
                        Toast.makeText(context, "Аккаунт удалён", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(DarkButtonColor)
                ) {
                    Text(
                        text = "удалить аккаунт",
                        color = Color.White
                    )
                }
            }
        }
    }
}