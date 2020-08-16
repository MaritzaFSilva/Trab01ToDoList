package com.todo.trab01todolist.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.todo.trab01todolist.R
import com.todo.trab01todolist.model.Task
import kotlinx.android.synthetic.main.item_task_edit.view.*
import kotlinx.android.synthetic.main.item_task_show.view.*

class TaskAdapter(val tasks: MutableList<Task>, val taskListener: TaskAdapterListener, context: Context) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private var selectedTask: Task? = null

    override fun getItemViewType(position: Int): Int {
        val task = tasks[position]
        // retorna a view Edit caso tenha uma tarefa selecionada, caso contrario exibe apenas a visualização dela.
        return if (task == selectedTask) {
            R.layout.item_task_edit
        } else {
            R.layout.item_task_show
        }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(task: Task) {


            if (selectedTask == task) {
                // SE TIVER UMA TAREFA SELECIONADA
                if (tasks.indexOf(task) == 0 && task?.id ==0L) {
                    itemView.edtTitle.setText("")
                    itemView.edtDescription.setText("")
                    itemView.btSave.setOnClickListener{
                        task.title =  itemView.edtTitle.text.toString()
                        task.description = itemView.edtDescription.text.toString()
                        task.status = false
                        with(this@TaskAdapter){
                            taskListener.onTaskSaved(task)
                        }
                        selectedTask = null
                        notifyItemInserted(tasks.indexOf(task))



                    }
                    itemView.btDelete.setOnClickListener{
                        selectedTask = null
                        tasks.remove(task)
                        notifyItemRemoved(tasks.indexOf(task))
                    }
                }else{

                    itemView.edtTitle.setText(task.title)
                    itemView.edtDescription.setText(task.description)
                    itemView.btSave.setOnClickListener{
                        with(this@TaskAdapter){
                            task.title = itemView.edtTitle.text.toString()
                            task.description = itemView.edtDescription.text.toString()
                            taskListener.onTaskUpdated(task)
                            selectedTask = null
                            notifyItemChanged(tasks.indexOf(task))
                        }
                    }
                    itemView.btDelete.setOnClickListener{
                        with(this@TaskAdapter){
                            notifyItemRemoved(tasks.indexOf(task))
                            taskListener.onTaskDeleted(task)
                            tasks.removeAt(tasks.indexOf(task))
                            selectedTask = null

                        }
                    }

                }
            } else {

                if (task.status) {
                    itemView.txtTaskTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    itemView.btShare.visibility = View.VISIBLE
                } else {
                    itemView.txtTaskTitle.paintFlags = 0
                    itemView.btShare.visibility = View.GONE
                }
                // SE NÃO TIVER TAREFA SELECIONADA


                itemView.txtTaskTitle.text = task.title

                itemView.setOnClickListener {
                    selectedTask = null
                    notifyItemChanged(tasks.indexOf(task))
                    editTask(task)

                }

                itemView.setOnLongClickListener {
                    task.status = !task.status

                    taskListener.onTaskUpdated(task)
                    notifyItemChanged(tasks.indexOf(task))

                    return@setOnLongClickListener true
                }
                itemView.btShare.setOnClickListener{
                    taskListener.onTaskShared(task)
                }
                if(task.status){
                    itemView.btShare.visibility = View.VISIBLE
                }


            }


        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.fillView(task)
    }

    fun insertTask(task: Task) {
        //Se não tiver nenhuma tarefa selecionada
        if(selectedTask == null){
            tasks.add(0, task)
            selectedTask = task
            notifyItemInserted(0)
            //coloca no log
            Log.d("TaskAdapter","Inserindo Tarefa id="+task.id)
        }



    }

    fun editTask(task: Task) {
        //Define a tarefa que recebeu como como a Tarefa selecionada
        selectedTask = task
        notifyItemChanged(tasks.indexOf(task))

        //coloca no log
        Log.d("TaskAdapter","Editando Tarefa id="+task.id)


    }




}