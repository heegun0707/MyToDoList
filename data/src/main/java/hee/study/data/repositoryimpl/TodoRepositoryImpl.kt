package hee.study.data.repositoryimpl

import hee.study.data.mapper.mapperToEntity
import hee.study.data.model.TodoEntity
import hee.study.data.remote.AdviceService
import hee.study.data.source.AppDatabase
import hee.study.domain.model.Advice
import hee.study.domain.model.TodoItem
import hee.study.domain.repository.TodoRepository
import hee.study.domain.utils.TodoStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val service: AdviceService
) : TodoRepository {

    override suspend fun getTodoList(): Flow<List<TodoItem>> /*= flow*/ {
        return database.todoDao().getTodoList().map {
            mapperToEntity(it)
        }.flowOn(Dispatchers.IO)
    }/*= flow {
        val list = ArrayList<TodoItem>()
        for (i: Int in 1..10) {
            list.add(
                TodoItem(
                    id = i,
                    title = (i * i).toString(),
                    startDate = Date(),
                    status = if (i % 2 == 0) TodoStatus.BEFORE else TodoStatus.DOING,
                    isFavorite = false
                )
            )
        }
        emit(list)
    }.flowOn(Dispatchers.IO)*/

    override suspend fun getTodoCount(): Flow<Pair<Int, Int>> {
        val totalCount = database.todoDao().getTotalTodoCount()
        val completedCount = database.todoDao().getCompletedCount()
        return flow {
            emit(Pair(totalCount, completedCount))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertTodo(todoItem: TodoItem) {
        val todo = TodoEntity(
            title = todoItem.title,
            memo = todoItem.memo,
            startDate = todoItem.startDate,
            endDate = todoItem.endDate,
            status = todoItem.status,
            isFavorite = todoItem.isFavorite
        )
        database.todoDao().insertTodo(todo)
    }

    override suspend fun updateFavorite(id: Long, isFavorite: Boolean) {
        Timber.e("isChecked: $isFavorite")
        database.todoDao().updateFavorite(id, isFavorite)
    }

    override suspend fun updateStatus(id: Long, status: TodoStatus) {
        database.todoDao().updateStatus(id, status.name)
    }

    override suspend fun updateTodo(todoItem: TodoItem) {
        val todo = TodoEntity(
            title = todoItem.title,
            memo = todoItem.memo,
            startDate = todoItem.startDate,
            endDate = todoItem.endDate,
            status = todoItem.status,
            isFavorite = todoItem.isFavorite
        )
        database.todoDao().updateTodo(todo)
    }

    override suspend fun deleteTodo(id: Long) {
        return database.todoDao().deleteTodo(id)
    }

    override suspend fun getAdvice(): Flow<Advice> = flow {
        emit(service.getRandomAdvice())
    }.flowOn(Dispatchers.IO).map {
        Advice(
            id = it.slip.id,
            advice = it.slip.advice
        )
    }
}