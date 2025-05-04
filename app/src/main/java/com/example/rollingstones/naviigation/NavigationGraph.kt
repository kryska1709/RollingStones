package com.example.rollingstones.naviigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rollingstones.view.AuthView
import com.example.rollingstones.view.HomeView
import com.example.rollingstones.view.RegView

@Composable
fun NavigationGraph(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.AuthScreen.route
    ){
        composable(
            route = Screen.AuthScreen.route
        ){
            AuthView(navHostController)
        }
        composable(
            route = Screen.RegScreen.route
        ){
            RegView(navHostController)
        }
        composable(
            route = Screen.LoadingScreen.route
        ){

        }
        composable(
            route = Screen.UserHomeScreen.route
        ){
            HomeView(navHostController)
        }
        composable(
            route = Screen.UserSettingsScreen.route
        ){

        }
        composable(
            route = Screen.AdminHomeScreen.route
        ){

        }
    }
}