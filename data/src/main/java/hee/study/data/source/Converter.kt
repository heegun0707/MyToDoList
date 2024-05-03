package hee.study.data.source

import androidx.room.TypeConverter
import hee.study.domain.utils.TodoStatus
import java.util.*

class Converter {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }

    @TypeConverter
    fun fromTodoStatus(todoStatus: TodoStatus): String {
        return todoStatus.name
    }

    @TypeConverter
    fun toTodoStatus(todoStatusString: String): TodoStatus {
        return enumValueOf(todoStatusString)
    }
}