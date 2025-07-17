
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Help & Support") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Welcome to the Student Study & Time Management App Help Center!",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            HelpCard(
                title = "How to Use the App",
                content = "Navigate through different sections using the bottom menu. You can manage tasks, view your study calendar, check notifications, and adjust settings from the dashboard."
            )

            HelpCard(
                title = "Managing Tasks",
                content = "Go to the 'Tasks' screen to add, edit, or delete tasks. Each task can have a deadline and status to track your progress."
            )

            HelpCard(
                title = "Using the Calendar",
                content = "The calendar shows your scheduled tasks and study sessions. Tap on a date to view tasks for that day."
            )

            HelpCard(
                title = "Notifications",
                content = "You'll receive reminders for upcoming tasks or study sessions. You can turn notifications on/off in the settings."
            )

            HelpCard(
                title = "Profile & Settings",
                content = "Tap on 'Settings' to switch between light/dark mode, update your profile, or control notification preferences."
            )

            HelpCard(
                title = "Need More Help?",
                content = "If you're facing issues, try restarting the app. For persistent problems, reach out to your course instructor or refer to course documentation."
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "App Version: 1.0.0",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun HelpCard(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = content, fontSize = 15.sp)
        }
    }
}
