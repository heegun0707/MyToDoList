package hee.study.domain.repository

import hee.study.domain.model.Advice
import hee.study.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodoList(): Flow<List<TodoItem>>
    suspend fun getTodoCount(): Flow<Pair<Int, Int>>
    suspend fun insertTodo(todoItem: TodoItem)
    suspend fun deleteTodo(id: Int)
    suspend fun getAdvice(): Flow<Advice>
}