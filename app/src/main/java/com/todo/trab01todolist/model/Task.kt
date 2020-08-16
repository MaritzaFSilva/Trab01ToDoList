package com.todo.trab01todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "status")
    var status:Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id:Long =0

    override fun equals(other: Any?): Boolean {
        val task = other as Task?
        return this.id == task?.id
    }

}