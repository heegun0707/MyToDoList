package hee.study.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hee.study.data.model.TodoEntity
import hee.study.data.source.dao.TodoDao

@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private const val DB_NAME = "todo.db"
        fun getInstance(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            )
                .build()
    }
}