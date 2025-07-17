package com.malik21.malikstudentstudytime

import HelpScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.malik21.malikstudentstudytime.data.TaskRepository
import com.malik21.malikstudentstudytime.screen.AccountScreen
import com.malik21.malikstudentstudytime.screen.DashboardScreen
import com.malik21.malikstudentstudytime.screen.NotificationsScreen
import com.malik21.malikstudentstudytime.screen.SettingsScreen
import com.malik21.malikstudentstudytime.screen.SimpleCalendarScreen
import com.malik21.malikstudentstudytime.screen.TasksScreen
import com.malik21.malikstudentstudytime.ui.theme.MalikStudentStudyTimeTheme
import com.malik21.malikstudentstudytime.viewmodel.TaskViewModel
import com.malik21.malikstudentstudytime.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            val repository = TaskRepository(applicationContext)
            val taskViewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(repository))

            // ✅ Theme toggle state
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }

            // ✅ Apply theme using your theme file (update as per your setup)
            MalikStudentStudyTimeTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentRoute == "dashboard",
                                onClick = { navController.navigate("dashboard") },
                                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                label = { Text("Dashboard") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "tasks",
                                onClick = { navController.navigate("tasks") },
                                icon = { Icon(Icons.Default.Check, contentDescription = "Tasks") },
                                label = { Text("Tasks") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "account",
                                onClick = { navController.navigate("account") },
                                icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                                label = { Text("Account") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "settings",
                                onClick = { navController.navigate("settings") },
                                icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                                label = { Text("Settings") }
                            )
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("dashboard") {
                            DashboardScreen(navController = navController)
                        }
                        composable("tasks") {
                            TasksScreen(navController = navController, viewModel = taskViewModel)
                        }
                        composable("notification") {
                            NotificationsScreen(navController = navController, viewModel = taskViewModel)
                        }
                        composable("calendar") {
                            SimpleCalendarScreen(navController = navController, viewModel = taskViewModel)
                        }
                        composable("account") {
                            AccountScreen()
                        }
                        composable("help") {
                            HelpScreen(navController = navController)
                        }

                        // ✅ Pass theme toggle handler to settings
                        composable("settings") {
                            SettingsScreen(
                                navController = navController,
                                onThemeToggle = { isDarkTheme = it }
                            )
                        }
                    }
                }
            }
        }
    }
}
