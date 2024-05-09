package hee.study.data.source.dao

import androidx.room.*
import hee.study.data.model.TodoEntity
import hee.study.domain.utils.TodoStatus
import io.reactivex.Completable
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    // Day
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todoItem: TodoEntity)

    @Query("SELECT * FROM todo ORDER BY CASE WHEN isFavorite = 1 THEN 0 ELSE 1 END")
    fun getTodoList() : Flow<List<TodoEntity>>

    @Query("SELECT COUNT(*) FROM todo")
    suspend fun getTotalTodoCount() : Int

    @Query("SELECT COUNT(*) FROM todo WHERE status = 'COMPLETED'")
    suspend fun getCompletedCount() : Int

    @Query("UPDATE todo SET isFavorite =:isFavorite WHERE id =:id")
    suspend fun updateFavorite(id: Long, isFavorite: Boolean)

    @Query("UPDATE todo SET status =:status WHERE id =:id")
    suspend fun updateStatus(id: Long, status: String)

    @Update
    suspend fun updateTodo(todoItem: TodoEntity)

    @Query("DELETE FROM todo WHERE id =:id")
    fun deleteTodo(id: Long)
}