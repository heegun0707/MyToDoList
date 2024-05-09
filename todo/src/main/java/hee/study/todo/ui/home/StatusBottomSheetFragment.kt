package hee.study.todo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import hee.study.domain.utils.TodoStatus
import hee.study.todo.R
import hee.study.todo.databinding.FragmentStatusBottomSheetBinding

@AndroidEntryPoint
class StatusBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentStatusBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_status_bottom_sheet, container, false)
        binding.imgCancel.setOnClickListener { dismiss() }
        binding.imgBefore.setOnClickListener {
            viewModel.updateStatus(viewModel.getSelectedItem().id, TodoStatus.BEFORE)
            dismiss()
        }
        binding.imgDoing.setOnClickListener {
            viewModel.updateStatus(viewModel.getSelectedItem().id, TodoStatus.DOING)
            dismiss()
        }
        binding.imgCompleted.setOnClickListener {
            viewModel.updateStatus(viewModel.getSelectedItem().id, TodoStatus.COMPLETED)
            dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}