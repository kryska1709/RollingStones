package com.example.rollingstones.naviigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rollingstones.view.AuthView
import com.example.rollingstones.view.HomeView
import com.example.rollingstones.view.InfoView
import com.example.rollingstones.view.LoadingView
import com.example.rollingstones.view.ProfilView
import com.example.rollingstones.view.RegView
import com.example.rollingstones.viewmodel.AuthViewModel
import com.example.rollingstones.viewmodel.BookingViewModel

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    bookingViewModel: BookingViewModel,
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.LoadingScreen.route
    ){
        composable(
            route = Screen.AuthScreen.route
        ){
            AuthView(navHostController, authViewModel)
        }
        composable(
            route = Screen.RegScreen.route
        ){
            RegView(navHostController, authViewModel)
        }
        composable(
            route = Screen.LoadingScreen.route
        ){
            LoadingView(navHostController,authViewModel)
        }
        composable(
            route = Screen.UserHomeScreen.route
        ){
            HomeView(navHostController,authViewModel,bookingViewModel)
        }
        composable(
            route = Screen.UserSettingsScreen.route
        ){
            ProfilView(navHostController,authViewModel)
        }
        composable(
            route = Screen.AdminHomeScreen.route
        ){

        }
        composable(
            route = Screen.InfoScreen.route
        ) {
            InfoView(navHostController)
        }
    }
}