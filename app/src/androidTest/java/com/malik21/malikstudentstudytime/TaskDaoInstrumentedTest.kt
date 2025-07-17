package com.malik21.malikstudentstudytime
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith




import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

import com.malik21.malikstudentstudytime.data.TaskDao
import com.malik21.malikstudentstudytime.data.TaskDatabase
import com.malik21.malikstudentstudytime.data.TaskItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Before


@RunWith(AndroidJUnit4::class)
class TaskDaoInstrumentedTest {

    private lateinit var taskDao: TaskDao
    private lateinit var db: TaskDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java)
            .allowMainThreadQueries() // Only for testing
            .build()
        taskDao = db.taskDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAndRetrieveTask() = runBlocking {
        val task = TaskItem(title = "Test Task", dueDate = "2025-07-17", isCompleted = false)
        taskDao.insertTask(task)

        val allTasks = taskDao.getAllTasks().first()
        assertEquals(1, allTasks.size)
        assertEquals("Test Task", allTasks[0].title)
    }

    @Test
    fun updateTaskTest() = runBlocking {
        val task = TaskItem(title = "Initial", dueDate = "2025-07-17", isCompleted = false)
        taskDao.insertTask(task)

        val inserted = taskDao.getAllTasks().first().first()
        val updatedTask = inserted.copy(title = "Updated Title", isCompleted = true)

        taskDao.updateTask(updatedTask)

        val result = taskDao.getAllTasks().first().first()
        assertEquals("Updated Title", result.title)
        assertEquals(true, result.isCompleted)
    }

    @Test
    fun deleteTaskTest() = runBlocking {
        val task = TaskItem(title = "To Delete", dueDate = "2025-07-17")
        taskDao.insertTask(task)

        val inserted = taskDao.getAllTasks().first().first()
        taskDao.deleteTask(inserted)

        val allTasks = taskDao.getAllTasks().first()
        assertEquals(0, allTasks.size)
    }
}
////}
//@RunWith(AndroidJUnit4::class)
//class BasicInstrumentedTest {
//    @Test
//    fun contextLoads() {
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.malik21.malikstudentstudytime", appContext.packageName)
//    }
//}
