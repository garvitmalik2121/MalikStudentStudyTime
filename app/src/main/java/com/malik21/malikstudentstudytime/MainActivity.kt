@file:OptIn(ExperimentalMaterial3Api::class)

package com.malik21.malikstudentstudytime

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malik21.malikstudentstudytime.ui.theme.MalikStudentStudyTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MalikStudentStudyTimeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DashboardScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { DashboardTopBar() },
        bottomBar = { DashboardBottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ProfileHeader(
                userName = "Garvit Malik",
                profileImage = painterResource(id = R.drawable.profile_pic)
            )

            Spacer(modifier = Modifier.height(16.dp))
            DashboardGrid()
        }
    }
}

@Composable
fun DashboardTopBar() {
    TopAppBar(
        title = { Text("Student Dashboard") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF3F51B5),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun DashboardBottomNavigationBar() {
    val context = LocalContext.current

    NavigationBar(
        containerColor = Color(0xFF3F51B5),
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {
                Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
            },
            icon = {
                Icon(Icons.Rounded.Home, contentDescription = "Home", tint = Color.White)
            },
            label = { Text("Home", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                Toast.makeText(context, "Cart clicked", Toast.LENGTH_SHORT).show()
            },
            icon = {
                Icon(Icons.Rounded.ShoppingCart, contentDescription = "Cart", tint = Color.White)
            },
            label = { Text("Cart", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                Toast.makeText(context, "Account clicked", Toast.LENGTH_SHORT).show()
            },
            icon = {
                Icon(Icons.Rounded.AccountCircle, contentDescription = "Account", tint = Color.White)
            },
            label = { Text("Account", color = Color.White) }
        )
    }
}

@Composable
fun ProfileHeader(userName: String, profileImage: Painter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                    .border(2.dp, Color.Gray, CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Button(onClick = { /* Navigate to Edit Profile */ }) {
            Text("Edit Profile")
        }
    }
}

@Composable
fun DashboardGrid() {
    val cards = listOf(
        Pair("Tasks", Icons.Default.Check),
        Pair("Messages", Icons.Default.Email),
        Pair("Calendar", Icons.Default.DateRange),
        Pair("Analytics", Icons.Default.Info),
        Pair("Settings", Icons.Default.Settings),
        Pair("Help", Icons.Default.Info)
    )

    Column {
        for (i in cards.indices step 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DashboardCard(title = cards[i].first, icon = cards[i].second)
                if (i + 1 < cards.size) {
                    DashboardCard(title = cards[i + 1].first, icon = cards[i + 1].second)
                }
            }
        }
    }
}

@Composable
fun DashboardCard(title: String, icon: ImageVector) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .width(160.dp)
            .height(120.dp)
            .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                Toast.makeText(context, "$title Clicked", Toast.LENGTH_SHORT).show()
            }
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFF3F51B5),
            modifier = Modifier.size(36.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun TestLayout(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .background(Color.Yellow),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(6) {
                val context = LocalContext.current
                Image(
                    painter = painterResource(id = image_ids[it]),
                    contentDescription = "Dice ${it + 1}",
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            Toast.makeText(context, "Click $it", Toast.LENGTH_SHORT).show()
                        }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .background(Color.Gray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Column 2")
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.Green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Column 3")
        }
    }
}

@Composable
fun TestLoginScreen(modifier: Modifier = Modifier) {
    // Placeholder for future login screen
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    MalikStudentStudyTimeTheme {
        DashboardScreen()
    }
}

private val image_ids = listOf(
    R.drawable.dice_1,
    R.drawable.dice_2,
    R.drawable.dice_3,
    R.drawable.dice_4,
    R.drawable.dice_5,
    R.drawable.dice_6
)
