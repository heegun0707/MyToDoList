package hee.study.data.source.dao

import androidx.room.*
import hee.study.data.model.TodoEntity
import io.reactivex.Completable
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    // Day
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todoItem: TodoEntity)

    @Query("SELECT * FROM todo")
    fun getTodoList() : Flow<List<TodoEntity>>

    @Query("SELECT COUNT(*) FROM todo")
    suspend fun getTotalTodoCount() : Int

    @Query("SELECT COUNT(*) FROM todo WHERE status = 'COMPLETED'")
    suspend fun getCompletedCount() : Int

    @Query("DELETE FROM todo WHERE id =:id")
    fun deleteTodo(id: Int)
}