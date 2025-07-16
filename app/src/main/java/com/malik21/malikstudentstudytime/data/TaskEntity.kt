package com.malik21.malikstudentstudytime.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskItem(
    val title: String,
    val dueDate: String,   // Required due date in "yyyy-MM-dd" format
    val isCompleted: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
