package hee.study.domain.model

import hee.study.domain.utils.TodoStatus
import java.util.Date

data class TodoItem(
    var id: Long = 0,
    var title: String,
    var memo: String? = null,
    var startDate: Date = Date(),
    var endDate: Date? = null,
    var status: TodoStatus,
    var isFavorite: Boolean
)
