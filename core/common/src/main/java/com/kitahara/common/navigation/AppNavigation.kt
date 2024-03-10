package com.kitahara.common.navigation

sealed class AppNavigation(val route: String) {
    data object LogIn : AppNavigation("logIn")
    data object Home : AppNavigation("home")
}