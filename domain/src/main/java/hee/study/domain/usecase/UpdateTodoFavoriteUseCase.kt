package hee.study.domain.usecase

import hee.study.domain.repository.TodoRepository
import javax.inject.Inject

class UpdateTodoFavoriteUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend fun execute(id: Long, isFavorite: Boolean) = repository.updateFavorite(id, isFavorite)
}