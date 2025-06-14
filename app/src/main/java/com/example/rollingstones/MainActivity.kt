package com.example.rollingstones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rollingstones.components.SideBarMenu
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.naviigation.NavigationGraph
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.ui.theme.RollingStonesTheme
import com.example.rollingstones.viewmodel.AdminViewModel
import com.example.rollingstones.viewmodel.AuthViewModel
import com.example.rollingstones.viewmodel.BookingViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authViewModel = viewModel<AuthViewModel>()
            val bookingViewModel = viewModel<BookingViewModel>()
            val adminViewModel = viewModel<AdminViewModel>()
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val navBackStackEntry = navController.currentBackStackEntryAsState()//следить за навигацией
            val currentRoute = navBackStackEntry.value?.destination?.route // получить текущий экран
            RollingStonesTheme {
                SideBarMenu(navController,authViewModel) { drawerState ->
                    Scaffold(
                        topBar = {
                            if(currentRoute != "auth_screen" && currentRoute !="reg_screen" && currentRoute !="loading_screen")
                            TopAppBar(
                                title = { Text(
                                    text = "Rolling Stones",
                                    color = Color.White,
                                    fontSize = 34.sp,
                                    fontFamily = Bibliothy,
                                    fontWeight = FontWeight.Thin)},
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MainColor
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                drawerState.open()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(Color.White)
                                .padding(innerPadding)
                        ) {
                            NavigationGraph(navController, authViewModel,bookingViewModel,adminViewModel)
                        }
                    }
                }
            }
        }
    }
}

