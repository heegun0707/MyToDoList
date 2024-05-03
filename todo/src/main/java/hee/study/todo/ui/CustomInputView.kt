package hee.study.todo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import hee.study.domain.utils.TodoStatus
import hee.study.todo.R
import hee.study.todo.databinding.CustomInputViewBinding

class CustomInputView(context: Context?, attrs: AttributeSet)
    : LinearLayout(context, attrs) {
    private var binding =
        CustomInputViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        getAttrs(attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun getAttrs(attrs: AttributeSet?) {
        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.InputViewInfo).apply {
                binding.tvTitle.text = getString(R.styleable.InputViewInfo_title)
                recycle()
            }
        }
    }

    fun getContent() = binding.tvContent.toString()

    fun getStatus() = when (binding.tvContent.toString()) {
        else -> TodoStatus.BEFORE
    }
}