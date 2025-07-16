//@file:OptIn(ExperimentalMaterial3Api::class)

package com.malik21.malikstudentstudytime


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
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.malik21.malikstudentstudytime.data.TaskRepository
import com.malik21.malikstudentstudytime.screen.AccountScreen
import com.malik21.malikstudentstudytime.screen.DashboardScreen
import com.malik21.malikstudentstudytime.screen.NotificationsScreen
import com.malik21.malikstudentstudytime.screen.SettingsScreen
import com.malik21.malikstudentstudytime.screen.SimpleCalendarScreen
import com.malik21.malikstudentstudytime.screen.TasksScreen
import com.malik21.malikstudentstudytime.viewmodel.TaskViewModel
import com.malik21.malikstudentstudytime.viewmodel.TaskViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val repository = TaskRepository(applicationContext)
            val viewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(repository))

            Scaffold(
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = navController.currentDestination?.route == "dashboard",
                            onClick = { navController.navigate("dashboard") },
                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                            label = { Text("Dashboard") }
                        )
                        NavigationBarItem(
                            selected = navController.currentDestination?.route == "tasks",
                            onClick = { navController.navigate("tasks") },
                            icon = { Icon(Icons.Default.Check, contentDescription = "Tasks") },
                            label = { Text("Tasks") }
                        )
                        NavigationBarItem(
                            selected = navController.currentDestination?.route == "account",
                            onClick = { navController.navigate("account") },
                            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                            label = { Text("Account") }
                        )
                        NavigationBarItem(
                            selected = navController.currentDestination?.route == "settings",
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
                        TasksScreen(navController = navController, viewModel = viewModel)
                    }
                    composable("notification") {
                        NotificationsScreen(navController = navController, viewModel = viewModel)
                    }
                    composable("calendar") {
                        SimpleCalendarScreen(navController = navController, viewModel = viewModel)
                    }
//                    composable("analytics") {
//                        AnalyticsScreen(navController = navController)
//                    }
                    composable("account") {
                        AccountScreen(navController = navController)
                    }
                    composable("settings") {
                        SettingsScreen(navController = navController)
                    }
//                    composable("help") {
//                        HelpScreen(navController = navController)
//                    }
                }
            }
        }
    }
}


//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Check
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.rounded.AccountCircle
//import androidx.compose.material.icons.rounded.Home
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.runtime.toMutableStateList
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.malik21.malikstudentstudytime.data.TaskRepository
//import com.malik21.malikstudentstudytime.ui.theme.MalikStudentStudyTimeTheme
//import kotlinx.coroutines.launch
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MalikStudentStudyTimeTheme {
//                AppNavigation()
//            }
//        }
//    }
//}
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "Dashboard") {
//        composable("Dashboard") { DashboardScreen(navController) }
//        composable("Tasks") { TasksScreen(navController) }
//    }
//}
//
//@Composable
//fun DashboardScreen(navController: NavController, modifier: Modifier = Modifier) {
//    Scaffold(
//        topBar = { DashboardTopBar() },
//        bottomBar = { DashboardBottomNavigationBar(navController) }
//    ) { paddingValues ->
//        Column(
//            modifier = modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Spacer(modifier = Modifier.height(16.dp))
//            ProfileHeader(
//                userName = "Garvit Malik",
//                profileImage = painterResource(id = R.drawable.profile_pic),
//                navController = navController
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//            DashboardGrid()
//        }
//    }
//}
//
//@Composable
//fun DashboardTopBar() {
//    TopAppBar(
//        title = { Text("Student Dashboard") },
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = Color(0xFF3F51B5),
//            titleContentColor = Color.White
//        )
//    )
//}
//
//@Composable
//fun DashboardBottomNavigationBar(navController: NavController) {
//    NavigationBar(containerColor = Color(0xFF3F51B5), tonalElevation = 4.dp) {
//        NavigationBarItem(
//            selected = false,
//            onClick = { navController.navigate("Dashboard") },
//            icon = { Icon(Icons.Rounded.Home, contentDescription = "Home", tint = Color.White) },
//            label = { Text("Home", color = Color.White) }
//        )
//        NavigationBarItem(
//            selected = false,
//            onClick = { navController.navigate("Tasks") },
//            icon = { Icon(Icons.Default.Check, contentDescription = "Tasks", tint = Color.White) },
//            label = { Text("Tasks", color = Color.White) }
//        )
//        NavigationBarItem(
//            selected = false,
//            onClick = {},
//            icon = { Icon(Icons.Rounded.AccountCircle, contentDescription = "Account", tint = Color.White) },
//            label = { Text("Account", color = Color.White) }
//        )
//    }
//}
//
//@Composable
//fun ProfileHeader(userName: String, profileImage: Painter, navController: NavController) {
//    Row(
//        modifier = Modifier.fillMaxWidth().padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Image(
//                painter = profileImage,
//                contentDescription = "Profile Picture",
//                modifier = Modifier.size(64.dp).clip(CircleShape).border(2.dp, Color.Gray, CircleShape)
//            )
//            Spacer(modifier = Modifier.width(12.dp))
//            Text(text = userName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        }
//        Button(onClick = { navController.navigate("Tasks") }) {
//            Text("Edit Profile")
//        }
//    }
//}
//
//@Composable
//fun DashboardGrid() {
//    val cards = listOf(
//        "Tasks" to Icons.Default.Check,
//        "Messages" to Icons.Default.Email,
//        "Calendar" to Icons.Default.DateRange,
//        "Analytics" to Icons.Default.Info,
//        "Settings" to Icons.Default.Settings,
//        "Help" to Icons.Default.Info
//    )
//    Column {
//        for (i in cards.indices step 2) {
//            Row(
//                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                DashboardCard(cards[i].first, cards[i].second)
//                if (i + 1 < cards.size) {
//                    DashboardCard(cards[i + 1].first, cards[i + 1].second)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DashboardCard(title: String, icon: ImageVector) {
//    val context = LocalContext.current
//    Column(
//        modifier = Modifier
//            .width(160.dp)
//            .height(120.dp)
//            .clip(MaterialTheme.shapes.medium)
//            .background(Color.White)
//            .clickable {
//                Toast.makeText(context, "$title Clicked", Toast.LENGTH_SHORT).show()
//            }
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Icon(imageVector = icon, contentDescription = title, tint = Color(0xFF3F51B5), modifier = Modifier.size(36.dp))
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
//    }
//}
//
//@Composable
//fun TasksScreen(navController: NavController, taskRepository: TaskRepository = TaskRepository(LocalContext.current)) {
//    val scope = rememberCoroutineScope()
//    val tasks by taskRepository.tasksFlow.collectAsState(initial = emptyList())
//    var showDialog by remember { mutableStateOf(false) }
//    val taskStates = remember(tasks) { tasks.map { mutableStateOf(it) }.toMutableStateList() }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Tasks") },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF3F51B5),
//                    titleContentColor = Color.White
//                )
//            )
//        },
//        bottomBar = { DashboardBottomNavigationBar(navController) },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { showDialog = true },
//                containerColor = Color(0xFF3F51B5)
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Add Task", tint = Color.White)
//            }
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)
//        ) {
//            if (taskStates.isEmpty()) {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    Text("No upcoming tasks", fontSize = 18.sp)
//                }
//            } else {
//                LazyColumn {
//                    items(taskStates) { taskState ->
//                        TaskCard(
//                            taskState = taskState,
//                            onDelete = {
//                                taskStates.remove(taskState)
//                                scope.launch {
//                                    taskRepository.saveTasks(taskStates.map { it.value })
//                                }
//                            }
//                        )
//                    }
//                }
//            }
//
//            if (showDialog) {
//                AddTaskDialog(
//                    onAdd = { title, dueDate ->
//                        val newTask = TaskItem(title, dueDate)
//                        taskStates.add(mutableStateOf(newTask))
//                        scope.launch {
//                            taskRepository.saveTasks(taskStates.map { it.value })
//                        }
//                        showDialog = false
//                    },
//                    onDismiss = { showDialog = false }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun AddTaskDialog(onAdd: (String, String?) -> Unit, onDismiss: () -> Unit) {
//    var title by remember { mutableStateOf("") }
//    var dateText by remember { mutableStateOf("") }
//
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    val dueDate = if (dateText.isNotBlank()) dateText else null
//                    if (title.isNotBlank()) onAdd(title, dueDate)
//                }
//            ) { Text("Add") }
//        },
//        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
//        title = { Text("New Task") },
//        text = {
//            Column {
//                OutlinedTextField(
//                    value = title,
//                    onValueChange = { title = it },
//                    label = { Text("Task Title") },
//                    singleLine = true
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = dateText,
//                    onValueChange = { dateText = it },
//                    label = { Text("Due Date (yyyy-MM-dd)") },
//                    singleLine = true
//                )
//            }
//        }
//    )
//}
//
//@Composable
//fun TaskCard(taskState: MutableState<TaskItem>, onDelete: () -> Unit) {
//    val task = taskState.value
//    Card(
//        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp).fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Column(modifier = Modifier.weight(1f)) {
//                Text(
//                    text = task.title,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
//                )
//                task.dueDate?.let {
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text("Due: $it", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
//                }
//            }
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Checkbox(
//                    checked = task.isCompleted,
//                    onCheckedChange = { isChecked -> taskState.value = task.copy(isCompleted = isChecked) }
//                )
//                IconButton(onClick = onDelete) {
//                    Icon(Icons.Default.Delete, contentDescription = "Delete Task")
//                }
//            }
//        }
//    }
//}
//
//data class TaskItem(val title: String, val dueDate: String? = null, val isCompleted: Boolean = false)
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DashboardScreenPreview() {
//    MalikStudentStudyTimeTheme {
//        DashboardScreen(navController = rememberNavController())
//    }
//}
