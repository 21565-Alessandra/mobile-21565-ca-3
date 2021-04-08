//ALESSANDRA SILVA DOS REIS -> ID 21565

package com.reis.todolist

//data class for each todoItem, and a list of those items is passed to the TodoAdapter
data class Todo (
    val title: String,
    var isChecked: Boolean = false
)
