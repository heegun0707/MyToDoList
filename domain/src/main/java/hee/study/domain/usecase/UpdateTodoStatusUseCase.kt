package hee.study.domain.usecase

import hee.study.domain.repository.TodoRepository
import hee.study.domain.utils.TodoStatus
import javax.inject.Inject

class UpdateTodoStatusUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend fun execute(id: Long, status: TodoStatus) = repository.updateStatus(id, status)
}