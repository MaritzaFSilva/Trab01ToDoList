package com.todo.trab01todolist.database.dao

import androidx.room.*
import com.todo.trab01todolist.model.Task

@Dao
interface TaskDAO {
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAll(): List<Task>

    @Insert
    fun insert(task:Task):Long

    @Update
    fun update(task:Task)

    @Delete
    fun delete(task: Task)

}