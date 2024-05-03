package hee.study.domain.usecase

import hee.study.domain.model.TodoItem
import hee.study.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend fun execute(): Flow<List<TodoItem>> = repository.getTodoList()
}