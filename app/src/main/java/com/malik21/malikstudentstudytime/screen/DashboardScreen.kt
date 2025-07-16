package com.malik21.malikstudentstudytime.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.malik21.malikstudentstudytime.R

@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F51B5))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ProfileHeader(
                userName = "Garvit Malik",
                profileImage = painterResource(id = R.drawable.ic_launcher_foreground),
                navController = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
            DashboardGrid(navController)
        }
    }
}

@Composable
fun ProfileHeader(userName: String, profileImage: androidx.compose.ui.graphics.painter.Painter, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = profileImage,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = userName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Button(onClick = { navController.navigate("account") }) {
            Text("Edit Profile")
        }
    }
}

@Composable
fun DashboardGrid(navController: NavController) {
    val cards = listOf(
        "Tasks" to Icons.Default.Check,
        "Messages" to Icons.Default.Email,
        "Calendar" to Icons.Default.DateRange,
        "Analytics" to Icons.Default.Info,
        "Settings" to Icons.Default.Settings,
        "Help" to Icons.Default.Info
    )

    Column {
        for (i in cards.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DashboardCard(cards[i].first, cards[i].second) {
                    if (cards[i].first == "Tasks") navController.navigate("tasks")
                }
                if (i + 1 < cards.size) {
                    DashboardCard(cards[i + 1].first, cards[i + 1].second) {
                        if (cards[i + 1].first == "Tasks") navController.navigate("tasks")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun DashboardCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(120.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = title, tint = Color(0xFF3F51B5), modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, fontWeight = FontWeight.Medium, fontSize = 16.sp)
        }
    }
}
