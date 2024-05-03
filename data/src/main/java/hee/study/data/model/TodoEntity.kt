package hee.study.data.model

import androidx.room.*
import hee.study.data.model.TodoEntity.Companion.TABLE_NAME
import hee.study.data.source.Converter
import hee.study.domain.utils.TodoStatus
import java.util.Date

@Entity(tableName = TABLE_NAME)
@TypeConverters(Converter::class)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int = 0,
    var title: String,
    var memo: String? = null,
    var startDate: Date,
    var endDate: Date? = null,
    var status: TodoStatus,
    var isFavorite: Boolean
) {
    companion object {
        const val TABLE_NAME = "todo"
        private const val COLUMN_ID = "id"
    }
}
