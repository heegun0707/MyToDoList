package hee.study.domain.usecase

import hee.study.domain.model.TodoItem
import hee.study.domain.repository.TodoRepository
import javax.inject.Inject

class UpdateTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend fun execute(todoItem: TodoItem) = repository.updateTodo(todoItem)
}