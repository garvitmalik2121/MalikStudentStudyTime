package com.malik21.malikstudentstudytime

import com.malik21.malikstudentstudytime.data.TaskItem
import com.malik21.malikstudentstudytime.data.toNotifications



import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TaskExtensionsTest {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @Test
    fun testToNotifications_withReminders() {
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)
        val tomorrow = today.plusDays(1)

        val tasks = listOf(
            TaskItem("Task Today", today.format(formatter), false),
            TaskItem("Task Yesterday", yesterday.format(formatter), false),
            TaskItem("Task Tomorrow", tomorrow.format(formatter), false),
            TaskItem("Task Invalid Date", "invalid-date", false)
        )

        val notifications = tasks.toNotifications()

        assertEquals(tasks.size, notifications.size)
        assertTrue(notifications[0].isReminder) // due today
        assertTrue(notifications[1].isReminder) // due yesterday
        assertFalse(notifications[2].isReminder) // due tomorrow
        assertFalse(notifications[3].isReminder) // invalid date
    }
}
