package hee.study.todo.ui.dashboard

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hee.study.todo.R
import hee.study.todo.base.BaseFragment
import hee.study.todo.databinding.FragmentDashboardBinding

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding, DashboardViewModel>(R.layout.fragment_dashboard) {
    override val homeViewModel: DashboardViewModel by viewModels()

    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.textDashboard.text = it
        }
    }
}