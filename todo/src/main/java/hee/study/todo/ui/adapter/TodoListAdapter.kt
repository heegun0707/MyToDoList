
package hee.study.todo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hee.study.todo.databinding.ItemTodoListBinding
import hee.study.domain.model.TodoItem
import hee.study.domain.utils.TodoStatus
import timber.log.Timber

class TodoListAdapter(
    private val onItemClickListener: (TodoItem) -> Unit,
    private val onItemStatusClickListener: (TodoItem) -> Unit,
    private val onItemFavClickListener: (Long, Boolean) -> Unit) :
    ListAdapter<TodoItem, TodoListAdapter.TodoViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TodoViewHolder(
        ItemTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class TodoViewHolder(private val binding: ItemTodoListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoItem) {
            binding.todo = todoItem
            itemView.setOnClickListener {
                onItemClickListener(todoItem)
            }
            binding.ckItemTodo.setOnCheckedChangeListener { _, _ ->
                onItemStatusClickListener(todoItem)
            }
            binding.ckItemTodoFav.setOnCheckedChangeListener { _, isChecked ->
                Timber.e("isChecked: $isChecked")
                onItemFavClickListener(todoItem.id, isChecked)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<TodoItem>() {
            override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}