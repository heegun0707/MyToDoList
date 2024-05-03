package hee.study.todo.ui.notifications

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hee.study.todo.R
import hee.study.todo.base.BaseFragment
import hee.study.todo.databinding.FragmentNotificationsBinding

@AndroidEntryPoint
class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>(R.layout.fragment_notifications) {
    override val homeViewModel: NotificationsViewModel by viewModels()

    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.textNotifications.text = it
        }
    }
}