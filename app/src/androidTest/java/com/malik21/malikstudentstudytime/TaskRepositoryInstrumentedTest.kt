package com.malik21.malikstudentstudytime



import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.malik21.malikstudentstudytime.data.TaskDatabase
import com.malik21.malikstudentstudytime.data.TaskItem
import com.malik21.malikstudentstudytime.data.TaskRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskRepositoryInstrumentedTest {

    private lateinit var db: TaskDatabase
    private lateinit var repository: TaskRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        repository = TaskRepository(context)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndRetrieveTask() = runBlocking {
        val task = TaskItem("Repo Test Task", "2025-07-17")
        repository.insertTask(task)

        val allTasks = repository.getAllTasks().first()
        Assert.assertTrue(allTasks.any { it.title == "Repo Test Task" })
    }

    @Test
    fun updateTask() = runBlocking {
        val task = TaskItem("Initial Title", "2025-07-17")
        repository.insertTask(task)

        val inserted = repository.getAllTasks().first().first()
        val updatedTask = inserted.copy(title = "Updated Title")

        repository.updateTask(updatedTask)

        val updated = repository.getAllTasks().first().first()
        Assert.assertEquals("Updated Title", updated.title)
    }


}
