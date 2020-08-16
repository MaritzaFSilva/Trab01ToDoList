package com.todo.trab01todolist.adapters

import com.todo.trab01todolist.model.Task

interface TaskAdapterListener {
    fun onTaskSaved(task: Task)
    fun onTaskUpdated(task: Task)
    fun onTaskDeleted(task: Task)
    fun onTaskShared(task: Task)
}