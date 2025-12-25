package com.example.taskify

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: MutableList<String>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkTask)
        val taskText: TextView = view.findViewById(R.id.taskText)
        val deleteBtn: TextView = view.findViewById(R.id.deleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskText.text = tasks[position]

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            holder.taskText.paintFlags =
                if (isChecked) holder.taskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                else holder.taskText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.deleteBtn.setOnClickListener {
            tasks.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = tasks.size
}