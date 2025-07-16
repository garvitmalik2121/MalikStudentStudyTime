package com.malik21.malikstudentstudytime.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.malik21.malikstudentstudytime.viewmodel.SettingsViewModel
import com.malik21.malikstudentstudytime.viewmodel.SettingsViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    onThemeToggle: (Boolean) -> Unit // you must handle this in App Theme
) {
    val context = LocalContext.current
    val viewModel: SettingsViewModel = viewModel(factory = SettingsViewModelFactory(context))

    val notificationsEnabled by viewModel.notificationsEnabled.collectAsState()
    val darkModeEnabled by viewModel.darkModeEnabled.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Notifications Toggle
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                ListItem(
                    headlineContent = { Text("Notifications") },
                    supportingContent = { Text("Enable or disable app notifications") },
                    leadingContent = { Icon(Icons.Default.Notifications, contentDescription = null) },
                    trailingContent = {
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { viewModel.toggleNotifications(it) }
                        )
                    }
                )
            }

            // Dark Mode Toggle
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                ListItem(
                    headlineContent = { Text("Dark Mode") },
                    supportingContent = { Text("Use dark theme across the app") },
                    leadingContent = { Icon(Icons.Default.Brightness4, contentDescription = null) },
                    trailingContent = {
                        Switch(
                            checked = darkModeEnabled,
                            onCheckedChange = {
                                viewModel.toggleDarkMode(it)
                                onThemeToggle(it)
                            }
                        )
                    }
                )
            }

            // Edit Profile
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("account") },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                ListItem(
                    headlineContent = { Text("Edit Profile") },
                    leadingContent = { Icon(Icons.Default.Edit, contentDescription = null) }
                )
            }

            // Help
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* navigate or open help */ },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                ListItem(
                    headlineContent = { Text("Help & Support") },
                    leadingContent = { Icon(Icons.Default.Help, contentDescription = null) }
                )
            }

            // About
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* show about info */ },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                ListItem(
                    headlineContent = { Text("About This App") },
                    supportingContent = { Text("Version 1.0.0") },
                    leadingContent = { Icon(Icons.Default.Info, contentDescription = null) }
                )
            }
        }
    }
}
