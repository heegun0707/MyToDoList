package hee.study.domain.usecase

import hee.study.domain.repository.TodoRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend fun execute(id: Int) = repository.deleteTodo(id)
}