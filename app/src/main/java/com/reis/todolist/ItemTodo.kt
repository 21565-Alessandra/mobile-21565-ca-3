//ALESSANDRA SILVA DOS REIS -> ID 21565

package com.reis.todolist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*

class ItemTodo : AppCompatActivity() {

    //global variables to be initialized later
    private lateinit var todoAdapter: TodoAdapter

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_todo)
        todoAdapter = TodoAdapter(mutableListOf())

        //to attach the TodoAdapter to the recycler view
        rvTodoItems.adapter = todoAdapter

        //to set how the items are arranged on the list
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val todoTitle = sharedPreferences.getString("TodoTitle", "")
        tvTodoTitle.text = todoTitle

        //to load the information saved with shared preferences
        btnLoad.setOnClickListener{

            val todoTitle = sharedPreferences.getString("TodoTitle", null)
            etTodoTitle.setText(todoTitle)


            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply()

        }

    }
}