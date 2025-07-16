package com.malik21.malikstudentstudytime

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.malik21.malikstudentstudytime.screen.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "dashboard") {
        composable("dashboard") { DashboardScreen(navController) }
        composable("tasks") { TasksScreen(navController) }
        composable("account") { AccountScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}
