package com.malik21.malikstudentstudytime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.malik21.malikstudentstudytime.data.TaskItem
import com.malik21.malikstudentstudytime.data.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskItem>>(emptyList())
    val tasks: StateFlow<List<TaskItem>> = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTasks().collect {
                _tasks.value = it
            }
        }
    }

    fun insertTask(task: TaskItem) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun updateTask(task: TaskItem) = viewModelScope.launch {
        repository.updateTask(task)
    }

    fun deleteTask(task: TaskItem) = viewModelScope.launch {
        repository.deleteTask(task)
    }
}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
