package com.malik21.malikstudentstudytime.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.malik21.malikstudentstudytime.TaskItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(name = "tasks_prefs")

class TaskRepository(private val context: Context) {
    private val TASKS_KEY = stringSetPreferencesKey("tasks")

    val tasksFlow: Flow<List<TaskItem>> = context.dataStore.data
        .map { preferences ->
            val jsonSet = preferences[TASKS_KEY] ?: emptySet()
            jsonSet.mapNotNull { json ->
                try {
                    Json.decodeFromString<TaskItem>(json)
                } catch (e: Exception) {
                    null
                }
            }
        }

    suspend fun saveTasks(tasks: List<TaskItem>) {
        val jsonSet = tasks.map { Json.encodeToString(it) }.toSet()
        context.dataStore.edit { preferences ->
            preferences[TASKS_KEY] = jsonSet
        }
    }
}
