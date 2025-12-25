package com.example.taskify

import android.os.Bundle
import android.text.style.StrikethroughSpan
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.taskInput)
        val addBtn = findViewById<Button>(R.id.addTaskBtn)
        val recycler = findViewById<RecyclerView>(R.id.taskRecycler)

        val adapter = TaskAdapter(tasks)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        addBtn.setOnClickListener {
            val task = input.text.toString()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyItemInserted(tasks.size - 1)
                input.text.clear()
            }
        }
    }
}