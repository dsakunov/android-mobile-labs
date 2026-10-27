package com.example.tasker.data.repository

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tasker.data.MockData
import com.example.tasker.data.database.AppDatabase
import com.example.tasker.data.database.TaskDao
import com.example.tasker.data.database.toEntity
import com.example.tasker.data.database.toTask
import com.example.tasker.domain.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object TaskRepository {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private lateinit var dao: TaskDao

    var tasks by mutableStateOf<List<Task>>(emptyList())
        private set

    var loaded by mutableStateOf(false)
        private set

    fun init(context: Context) {
        if (::dao.isInitialized) return
        val app = context.applicationContext
        SettingsRepository.init(app)
        val db = Room.databaseBuilder(app, AppDatabase::class.java, "tasker.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    MockData.initialTasks().map { it.toEntity() }.forEach { task ->
                        db.execSQL(
                            "INSERT INTO tasks (title, note, dateEpochDay, priority, done) VALUES (?, ?, ?, ?, ?)",
                            arrayOf(task.title, task.note, task.dateEpochDay, task.priority, if (task.done) 1 else 0)
                        )
                    }
                }
            })
            .build()
        dao = db.taskDao()
        scope.launch {
            dao.observeAll().collect { list ->
                tasks = list.map { it.toTask() }
                loaded = true
            }
        }
    }

    fun upsert(task: Task) {
        scope.launch { dao.insert(task.toEntity()) }
    }

    fun toggle(task: Task) = upsert(task.copy(done = !task.done))

    fun delete(id: Long) {
        scope.launch { dao.deleteById(id) }
    }
}
