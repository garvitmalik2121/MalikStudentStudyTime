package com.malik21.malikstudentstudytime.data


import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun List<TaskItem>.toNotifications(): List<TaskNotification> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val today = LocalDate.now()

    return this.mapIndexed { index, task ->
        val dueDateParsed = runCatching { LocalDate.parse(task.dueDate, formatter) }.getOrNull()
        val isReminder = dueDateParsed != null &&
                (dueDateParsed == today || dueDateParsed == today.minusDays(1))

        TaskNotification(
            id = index,
            title = task.title,
            dueDate = task.dueDate,
            isReminder = isReminder
        )
    }
}
