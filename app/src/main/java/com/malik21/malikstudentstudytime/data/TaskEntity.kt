package com.malik21.malikstudentstudytime.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskItem(
    val title: String,
    val dueDate: String? = null,
    val isCompleted: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
