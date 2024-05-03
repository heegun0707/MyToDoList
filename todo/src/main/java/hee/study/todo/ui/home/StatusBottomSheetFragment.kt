package hee.study.todo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import hee.study.domain.model.TodoItem
import hee.study.domain.utils.TodoStatus
import hee.study.todo.R
import hee.study.todo.databinding.FragmentAddBottomSheetBinding
import hee.study.todo.ui.home.HomeViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class StatusBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentAddBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun insertTodo() {
        val today = LocalDate.now()
        viewModel.insertTodo(
            TodoItem(
                title = binding.etTitle.toString(),
                memo = "binding.inputViewMemo.getContent()",
                endDate = Date.from(
                    today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()
                ),
                status = TodoStatus.BEFORE,
                isFavorite = binding.ckFavorite.isChecked
            )

        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}