package com.example.rollingstones.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.R
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun SideBarMenu(
    navController: NavController,
    authViewModel: AuthViewModel,
    action: @Composable (DrawerState) -> Unit
) {
    val configuration = LocalConfiguration.current
    val width = (configuration.screenWidthDp*0.65).dp
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isAdmin = authViewModel.isAdmin.collectAsState()
    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = false,
        drawerContent = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(Color.Transparent)
                    .clickable {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                        .background(BackGround)
                        .width(width)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logorolling),
                        contentDescription = null
                    )
                    when(isAdmin.value) {
                        true -> {
                            RowSideBarMenu("Главная", R.drawable.house, DarkButtonColor) {
                                navController.navigate(Screen.AdminHomeScreen.route)
                                scope.launch { drawerState.close() }
                            }

                            RowSideBarMenu("Правила", R.drawable.info_circle, DarkButtonColor) {
                                navController.navigate(Screen.RulesScreen.route)
                                scope.launch { drawerState.close() }
                            }
                        }
                        else -> {
                            RowSideBarMenu("Главная", R.drawable.house, DarkButtonColor) {
                                navController.navigate(Screen.UserHomeScreen.route)
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                            RowSideBarMenu(
                                "Профиль",
                                R.drawable.person_crop_circle,
                                DarkButtonColor
                            ) {
                                navController.navigate(Screen.UserSettingsScreen.route)
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                            RowSideBarMenu("О нас", R.drawable.info_circle, DarkButtonColor) {
                                navController.navigate(Screen.InfoScreen.route)
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    TextButton(
                        onClick = {
                            authViewModel.signOut()
                            navController.navigate(Screen.AuthScreen.route)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    ) {
                        Text(
                            text = "Выйти из аккаунта",
                            fontSize = 18.sp,
                            color = DarkButtonColor,
                            modifier = Modifier.padding(bottom = 20.dp, start = 15.dp)
                            )
                    }
                }
            }
        }
    ) {
        Box{
            action(drawerState)
        }
    }
}