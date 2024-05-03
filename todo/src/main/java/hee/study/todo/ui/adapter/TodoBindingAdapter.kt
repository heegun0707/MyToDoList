package hee.study.todo.ui.adapter

import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import hee.study.todo.R
import hee.study.domain.utils.TodoStatus

object TodoBindingAdapter {
    @JvmStatic
    @BindingAdapter(
        value = ["statusIcon"],
        requireAll = false
    )
    fun bindStatusIcon(
        view: CheckBox,
        type: TodoStatus,
    ) {
        val resId: Int = when (type) {
            TodoStatus.BEFORE -> R.drawable.ck_before
            TodoStatus.DOING -> R.drawable.ck_doing
            TodoStatus.COMPLETED -> R.drawable.ck_completed
        }
        view.setBackgroundResource(resId)
    }
}