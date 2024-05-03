
package hee.study.todo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hee.study.todo.databinding.ItemTodoListBinding
import hee.study.domain.model.TodoItem

class TodoListAdapter(private val onItemClickListener: (String) -> Unit) :
    ListAdapter<TodoItem, TodoListAdapter.TodoViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TodoViewHolder(
        ItemTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClickListener)
    }

    inner class TodoViewHolder(private val binding: ItemTodoListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoItem, onItemClickListener: (String) -> Unit) {
            binding.todo = todoItem
            itemView.setOnClickListener {
                onItemClickListener(todoItem.title)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<TodoItem>() {
            override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem.startDate == newItem.startDate
            }

            override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}