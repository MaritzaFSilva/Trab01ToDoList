package com.todo.trab01todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.todo.trab01todolist.database.dao.TaskDAO
import com.todo.trab01todolist.model.Task

@Database(entities = [Task::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDAO

}