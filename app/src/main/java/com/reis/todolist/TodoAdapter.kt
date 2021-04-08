//ALESSANDRA SILVA DOS REIS -> ID 21565

package com.reis.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter (
    private val todos: MutableList<Todo>
    ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    //class that is wrapped around a specific view
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    //to add new item to the list
    fun addTodo(todo: Todo) {
        todos.add(todo)
        //to notify new items added to the list
        notifyItemInserted(todos.size - 1)
    }

    //to delete done items of the list
    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        //after deleted it will notify of what was changed on the list
        notifyDataSetChanged()
    }

    //to set how the check box will react when clicked
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    //to assign the data of the list to the text view
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }

        }
    }

    //to get the size of the list
    override fun getItemCount(): Int {
        return todos.size
    }
}