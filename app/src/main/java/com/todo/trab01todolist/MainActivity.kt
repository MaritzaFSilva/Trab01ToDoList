package com.todo.trab01todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.todo.trab01todolist.adapters.TaskAdapter
import com.todo.trab01todolist.adapters.TaskAdapterListener
import com.todo.trab01todolist.database.AppDatabase
import com.todo.trab01todolist.database.dao.TaskDAO
import com.todo.trab01todolist.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TaskAdapterListener {
    private lateinit var adapter: TaskAdapter
    private lateinit var dao: TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "dbtasks")
            .allowMainThreadQueries().build()
        dao = db.taskDao()

        loadData()

        btAdd.setOnClickListener{

            adapter.insertTask(Task("","",false))
        }

    }

    private fun loadData() {
        val tasks = dao.getAll().toMutableList()


        adapter = TaskAdapter(tasks.toMutableList(),this,applicationContext)
        rvTasks.adapter = adapter
        rvTasks.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL, false)
    }


    override fun onTaskSaved(task: Task) {
        dao.insert(task)
        loadData()
    }

    override fun onTaskUpdated(task: Task){
        dao.update(task)
        loadData()
    }

    override fun onTaskDeleted(task: Task) {
        dao.delete(task)
    }

    override fun onTaskShared(task: Task) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT,getString(R.string.task_message)+task.title)

        if (shareIntent.resolveActivity(packageManager) != null) {
            startActivity(shareIntent)
        }


    }
}