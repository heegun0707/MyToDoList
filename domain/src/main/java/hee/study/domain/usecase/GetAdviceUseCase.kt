package hee.study.domain.usecase

import hee.study.domain.model.Advice
import hee.study.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAdviceUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend fun execute(): Flow<Advice> = repository.getAdvice()
}