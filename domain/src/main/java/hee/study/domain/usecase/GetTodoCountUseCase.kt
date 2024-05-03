package hee.study.domain.usecase

import hee.study.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoCountUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend fun execute(): Flow<Pair<Int, Int>> = repository.getTodoCount()
}