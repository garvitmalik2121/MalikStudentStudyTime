package com.malik21.malikstudentstudytime.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class TaskRepository(context: Context) {
    private val taskDao = TaskDatabase.getDatabase(context).taskDao()

    fun getAllTasks(): Flow<List<TaskItem>> = taskDao.getAllTasks()

    suspend fun insertTask(task: TaskItem) = taskDao.insertTask(task)

    suspend fun updateTask(task: TaskItem) = taskDao.updateTask(task)

    suspend fun deleteTask(task: TaskItem) = taskDao.deleteTask(task)
}





