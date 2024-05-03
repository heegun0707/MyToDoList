package hee.study.todo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val layoutId: Int
) : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected abstract val homeViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this@BaseFragment
        initViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        homeViewModel.clearDisposable()
    }

    protected open fun initView() {}
    protected open fun initViewModel() {}
}