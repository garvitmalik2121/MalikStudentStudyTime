package com.malik21.malikstudentstudytime.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.malik21.malikstudentstudytime.data.TaskNotification
import com.malik21.malikstudentstudytime.data.toNotifications
import com.malik21.malikstudentstudytime.viewmodel.TaskViewModel

@Composable
fun NotificationsScreen(navController: NavHostController, viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val notifications = tasks.toNotifications()

    NotificationList(notifications = notifications)
}

@Composable
fun NotificationList(notifications: List<TaskNotification>) {
    if (notifications.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("No notifications")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notifications) { notification ->
                NotificationCard(notification)
            }
        }
    }
}

@Composable
fun NotificationCard(notification: TaskNotification) {
    val redBackground = Color(0xFFFFCDD2)
    val normalBackground = MaterialTheme.colorScheme.surfaceVariant

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (notification.isReminder) redBackground else normalBackground
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Task Added: ${notification.title}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Due on: ${notification.dueDate}",
                style = MaterialTheme.typography.bodyMedium,
                color = if (notification.isReminder) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
