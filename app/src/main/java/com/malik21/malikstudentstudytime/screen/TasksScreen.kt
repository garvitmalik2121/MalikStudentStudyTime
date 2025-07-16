package com.malik21.malikstudentstudytime.screen

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malik21.malikstudentstudytime.R
import com.malik21.malikstudentstudytime.data.TaskItem
import com.malik21.malikstudentstudytime.viewmodel.TaskViewModel

@Composable
fun TasksScreen(navController: NavController, viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val context = LocalContext.current
    val player = remember { MediaPlayer.create(context, R.raw.click_sound) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasks") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            if (tasks.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No upcoming tasks")
                }
            } else {
                LazyColumn {
                    items(tasks) { task ->
                        TaskCard(task = task,
                            onCheckedChange = {
                                viewModel.updateTask(task.copy(isCompleted = it))
                                player.start()
                            },
                            onDelete = {
                                viewModel.deleteTask(task)
                                player.start()
                            })
                    }
                }
            }

            if (showDialog) {
                AddTaskDialog(onAdd = { title, dueDate ->
                    viewModel.insertTask(TaskItem(title, dueDate))
                    showDialog = false
                    player.start()
                }, onDismiss = {
                    showDialog = false
                })
            }
        }
    }
}

@Composable
fun TaskCard(task: TaskItem, onCheckedChange: (Boolean) -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isCompleted) androidx.compose.ui.text.style.TextDecoration.LineThrough else null
                )
                task.dueDate?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Due: $it", style = MaterialTheme.typography.bodyMedium)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onCheckedChange
                )
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Task")
                }
            }
        }
    }
}

@Composable
fun AddTaskDialog(onAdd: (String, String?) -> Unit, onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (title.isNotBlank()) {
                        val due = if (dueDate.isBlank()) null else dueDate
                        onAdd(title.trim(), due)
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add New Task") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Task Title") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date (optional)") },
                    singleLine = true
                )
            }
        }
    )
}
