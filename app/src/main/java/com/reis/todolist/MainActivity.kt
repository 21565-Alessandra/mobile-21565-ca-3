//ALESSANDRA SILVA DOS REIS -> ID 21565

package com.reis.todolist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    //global variables to be initialized later
    private lateinit var todoAdapter: TodoAdapter

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to take the todolist and display on the recycler view
        todoAdapter = TodoAdapter(mutableListOf())

        sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        //to attach the TodoAdapter to the recycler view
        rvTodoItems.adapter = todoAdapter

        //to set how the items are arranged on the list
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        //to add the user input to the list
        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()

            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }

            //to save the information with shared preferences
            editor.apply{
                putString("TodoTitle", todoTitle)
                apply()
            }

            //message to pop up on the screen saying that the user input is saved/added to the list
            Toast.makeText(this, "Item saved to the list", Toast.LENGTH_LONG).show()

        }

        //to delete the item once the user select the check box and press done
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()

        }
    }
}



