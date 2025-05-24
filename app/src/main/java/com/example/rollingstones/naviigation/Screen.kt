package com.example.rollingstones.naviigation

sealed class Screen(val route : String) {
    data object AuthScreen : Screen(route = "auth_screen")
    data object RegScreen : Screen(route = "reg_screen")
    data object UserHomeScreen : Screen(route = "user_home_screen")
    data object AdminHomeScreen : Screen(route = "admin_home_screen")
    data object UserSettingsScreen : Screen(route = "user_settings_screen")
    data object LoadingScreen : Screen(route = "loading_screen")
    data object InfoScreen : Screen(route = "info_screen")
}