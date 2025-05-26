package com.example.rollingstones.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rollingstones.R
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

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
                    RowSideBarMenu("Главная",R.drawable.webpagehome_85808, DarkButtonColor) {
                        navController.navigate(Screen.UserHomeScreen.route)
                        scope.launch {
                            drawerState.close()
                        }
                    }
                    RowSideBarMenu("Профиль",R.drawable.settings_user_profile_icon_188630, DarkButtonColor) {
                        navController.navigate(Screen.UserSettingsScreen.route)
                        scope.launch {
                            drawerState.close()
                        }
                    }
                    RowSideBarMenu("О нас",R.drawable.inform_icon_icons_com_67949, DarkButtonColor) {
                        navController.navigate(Screen.InfoScreen.route)
                        scope.launch {
                            drawerState.close()
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    RowSideBarMenu("выйти", R.drawable.cancel_close_delete, Color.Red) {
                        authViewModel.signOut()
                        navController.navigate(Screen.AuthScreen.route)
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