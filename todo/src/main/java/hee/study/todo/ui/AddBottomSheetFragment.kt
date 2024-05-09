package hee.study.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import hee.study.domain.model.TodoItem
import hee.study.domain.utils.TodoStatus
import hee.study.todo.R
import hee.study.todo.databinding.FragmentAddBottomSheetBinding
import hee.study.todo.ui.home.HomeViewModel
import hee.study.todo.ui.home.StatusBottomSheetFragment
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@AndroidEntryPoint
class AddBottomSheetFragment(
    private val isEditMode: Boolean = false
) : BottomSheetDialogFragment() {
    private var _binding: FragmentAddBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_bottom_sheet, container, false)
        binding.sheet = this
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        binding.imgCancel.setOnClickListener { dismiss() }
        binding.btnConfirm.setOnClickListener { insertOrUpdateTodo() }
        if (isEditMode) {
            binding.imgDelete.visibility = View.VISIBLE
            binding.etTitle.setText(viewModel.getSelectedItem().title)
            binding.ckFavorite.isChecked = viewModel.getSelectedItem().isFavorite
            binding.imgStatus.setBackgroundResource(
                when (viewModel.getSelectedItem().status) {
                    TodoStatus.BEFORE -> R.drawable.ck_before
                    TodoStatus.DOING -> R.drawable.ck_doing
                    TodoStatus.COMPLETED -> R.drawable.ck_completed
                }
            )
        }
    }

    private fun insertOrUpdateTodo() {
        val today = LocalDate.now()
        if (isEditMode) {
            viewModel.updateTodo(
                TodoItem(
                    id = viewModel.getSelectedItem().id,
                    title = binding.etTitle.text.toString(),
                    memo = "",
                    endDate = Date.from(
                        today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()
                    ),
                    status = viewModel.getSelectedItem().status,
                    isFavorite = binding.ckFavorite.isChecked
                )
            )
        } else {
            viewModel.insertTodo(
                TodoItem(
                    title = binding.etTitle.text.toString(),
                    memo = "",
                    endDate = Date.from(
                        today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()
                    ),
                    status = TodoStatus.BEFORE,
                    isFavorite = binding.ckFavorite.isChecked
                )
            )
        }
        dismiss()
    }

    fun deleteTodo() {
        viewModel.deleteTodo(viewModel.getSelectedItem().id)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}