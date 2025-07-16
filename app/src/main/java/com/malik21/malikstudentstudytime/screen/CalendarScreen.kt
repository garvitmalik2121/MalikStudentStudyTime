package com.malik21.malikstudentstudytime.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malik21.malikstudentstudytime.viewmodel.TaskViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun SimpleCalendarScreen(navController: NavController, viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val datesWithTasks = remember(tasks) {
        tasks.mapNotNull { it.dueDate }
            .mapNotNull { LocalDate.parse(it) }
            .toSet()
    }

    var currentYearMonth by remember { mutableStateOf(YearMonth.now()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        MonthNavigation(
            currentYearMonth = currentYearMonth,
            onPreviousMonth = { currentYearMonth = currentYearMonth.minusMonths(1) },
            onNextMonth = { currentYearMonth = currentYearMonth.plusMonths(1) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        DaysOfWeekHeader()

        Spacer(modifier = Modifier.height(4.dp))

        MonthDaysGrid(
            yearMonth = currentYearMonth,
            datesWithTasks = datesWithTasks,
            onDayClick = { /* handle day click if needed */ }
        )
    }
}

@Composable
fun MonthNavigation(
    currentYearMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    val monthYearText = currentYearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentYearMonth.year
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onPreviousMonth) {
            Text("< Prev")
        }
        Text(text = monthYearText, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        TextButton(onClick = onNextMonth) {
            Text("Next >")
        }
    }
}

@Composable
fun DaysOfWeekHeader() {
    val daysOfWeek = DayOfWeek.values()
    Row(modifier = Modifier.fillMaxWidth()) {
        for (day in daysOfWeek) {
            Text(
                text = day.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                maxLines = 1
            )
        }
    }
}

@Composable
fun MonthDaysGrid(
    yearMonth: YearMonth,
    datesWithTasks: Set<LocalDate>,
    onDayClick: (LocalDate) -> Unit
) {
    val firstDayOfMonth = yearMonth.atDay(1)
    val lastDayOfMonth = yearMonth.atEndOfMonth()
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // to shift Sunday to 0

    // Create a list with blanks for days before month starts
    val totalCells = firstDayOfWeek + lastDayOfMonth.dayOfMonth
    val daysList = (1..totalCells).map { index ->
        if (index <= firstDayOfWeek) null else index - firstDayOfWeek
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(daysList) { day ->
                if (day == null) {
                    Box(modifier = Modifier.size(40.dp))
                } else {
                    val date = yearMonth.atDay(day)
                    val hasTask = datesWithTasks.contains(date)
                    val bgColor = if (hasTask) Color(0xFF81C784) else Color.Transparent

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = bgColor, shape = MaterialTheme.shapes.small)
                            .clickable { onDayClick(date) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day.toString())
                    }
                }
            }
        }
    )
}
