package com.malik21.malikstudentstudytime.data

data class TaskNotification(
    val id: Int,
    val title: String,
    val dueDate: String,
    val isReminder: Boolean
)
