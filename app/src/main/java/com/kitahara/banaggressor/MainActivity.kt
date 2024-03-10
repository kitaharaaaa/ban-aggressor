package com.kitahara.banaggressor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hyperhoop.song_playback.presentation.ServiceManagement
import com.kitahara.common.navigation.AppNavigation
import com.kitahara.log_in.domain.LogInLauncher
import com.kitahara.log_in.presentation.LogInScreen
import com.kitahara.home.presentation.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var serviceManager: ServiceManagement

    @Inject
    lateinit var logInLauncher: LogInLauncher

    private val permissionForActivityResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it)
                serviceManager.launchService()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                   permissionForActivityResult.launch(Manifest.permission.POST_NOTIFICATIONS)
               } else serviceManager.launchService()
       */
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = AppNavigation.LogIn.route) {
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

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            serviceManager.launchService()
        } else if (requestCode == 1337) {

            //if API above or equals - we need request permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            } else serviceManager.launchService()
        }
    }
*/
    override fun onDestroy() {
        super.onDestroy()

        serviceManager.stopService()
    }
}



