package com.example.rollingstones.naviigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rollingstones.view.AdminHomeView
import com.example.rollingstones.view.AuthView
import com.example.rollingstones.view.HomeView
import com.example.rollingstones.view.InfoView
import com.example.rollingstones.view.LoadingView
import com.example.rollingstones.view.ProfileView
import com.example.rollingstones.view.RegView
import com.example.rollingstones.view.RulesView
import com.example.rollingstones.viewmodel.AdminViewModel
import com.example.rollingstones.viewmodel.AuthViewModel
import com.example.rollingstones.viewmodel.BookingViewModel

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    bookingViewModel: BookingViewModel,
    adminViewModel: AdminViewModel
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
            HomeView(authViewModel,bookingViewModel)
        }
        composable(
            route = Screen.UserSettingsScreen.route
        ){
            ProfileView(navHostController,authViewModel)
        }
        composable(
            route = Screen.AdminHomeScreen.route
        ){
            AdminHomeView(adminViewModel)
        }
        composable(
            route = Screen.InfoScreen.route
        ) {
            InfoView()
        }
        composable(
            route = Screen.RulesScreen.route
        ) {
            RulesView()
        }
    }
}