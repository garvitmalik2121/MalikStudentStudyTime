//package com.malik21.malikstudentstudytime
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.malik21.malikstudentstudytime.screen.AccountScreen
//import com.malik21.malikstudentstudytime.screen.DashboardScreen
//import com.malik21.malikstudentstudytime.screen.NotificationsScreen
//import com.malik21.malikstudentstudytime.screen.SettingsScreen
//import com.malik21.malikstudentstudytime.screen.SimpleCalendarScreen
//import com.malik21.malikstudentstudytime.screen.TasksScreen
//import com.malik21.malikstudentstudytime.viewmodel.TaskViewModel
//
//@Composable
//fun AppNavigation(navController: NavHostController, viewModel: TaskViewModel) {
//    NavHost(navController, startDestination = "dashboard") {
//        composable("dashboard") { DashboardScreen(navController) }
//        composable("tasks") { TasksScreen(navController, viewModel = viewModel) }
//        composable("notification") { NotificationsScreen(navController, viewModel = viewModel) }
//        composable("calendar") { SimpleCalendarScreen(navController, viewModel = viewModel) }
////        composable("analytics") { AnalyticsScreen(navController) }
//        composable("account") { AccountScreen(navController) }
//        composable("settings") { SettingsScreen(navController, onThemeToggle = { isDarkTheme = it }) }
////        composable("help") { HelpScreen(navController) }
//    }
//}
