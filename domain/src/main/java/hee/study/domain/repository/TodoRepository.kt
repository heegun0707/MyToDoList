package hee.study.domain.repository

import hee.study.domain.model.Advice
import hee.study.domain.model.TodoItem
import hee.study.domain.utils.TodoStatus
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodoList(): Flow<List<TodoItem>>
    suspend fun getTodoCount(): Flow<Pair<Int, Int>>
    suspend fun insertTodo(todoItem: TodoItem)
    suspend fun updateFavorite(id: Long, isFavorite: Boolean)
    suspend fun updateStatus(id: Long, status: TodoStatus)
    suspend fun updateTodo(todoItem: TodoItem)
    suspend fun deleteTodo(id: Long)
    suspend fun getAdvice(): Flow<Advice>
}