package hee.study.data.mapper

import hee.study.data.model.TodoEntity
import hee.study.domain.model.TodoItem

fun mapperToEntity(entity: List<TodoEntity>): List<TodoItem> {
    return entity.toList().map {
        TodoItem(
            it.title,
            it.memo,
            it.startDate,
            it.endDate,
            it.status,
            it.isFavorite
        )
    }
}