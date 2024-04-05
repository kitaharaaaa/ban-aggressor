package com.kitahara.banaggressor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kitahara.common.navigation.AppNavigation
import com.kitahara.home.presentation.HomeScreen
import com.kitahara.log_in.domain.LogInLauncher
import com.kitahara.log_in.presentation.LogInScreen
import com.kitahara.theme.BanAggressorTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var logInLauncher: LogInLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BanAggressorTheme{
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = AppNavigation.LogIn.route
                ) {
                    composable(AppNavigation.LogIn.route) {
                        LogInScreen(logInLauncher) {
                            navController.navigate(AppNavigation.Home.route) {
                                popUpTo(AppNavigation.Home.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }

                    composable(AppNavigation.Home.route) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}



