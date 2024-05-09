package hee.study.todo.ui.home

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hee.study.todo.R
import hee.study.todo.base.BaseFragment
import hee.study.todo.databinding.FragmentHomeBinding
import hee.study.todo.ui.AddBottomSheetFragment
import hee.study.todo.ui.adapter.TodoListAdapter
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val homeViewModel: HomeViewModel by activityViewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var adapter: TodoListAdapter

    override fun initView() {
        super.initView()
        binding.homeVm = homeViewModel
        binding.weatherVm = weatherViewModel
        initAdapter()
    }

    override fun initViewModel() {
        super.initViewModel()
        homeViewModel.apply {
            this.toDoList.observe(viewLifecycleOwner) {
                Timber.d("list : $it")
                adapter.submitList(it)
            }
            this.toDoPercent.observe(viewLifecycleOwner) {
                binding.tvTodayTodoPercent.text = String.format(requireContext().getString(R.string.today_todo_percent), it)
            }
            this.toDoCount.observe(viewLifecycleOwner) {
                binding.tvTodayTodoCount.text = String.format(requireContext().getString(R.string.today_todo_count), it.second, it.first)
            }
        }
    }

    private fun initAdapter() {
        adapter = TodoListAdapter(
            onItemClickListener = { item ->
                homeViewModel.setSelectedItem(item)
                val addSheetFragment = AddBottomSheetFragment(true)
                addSheetFragment.show(childFragmentManager, addSheetFragment.tag)
            },
            onItemStatusClickListener = { item ->
                homeViewModel.setSelectedItem(item)
                val statusSheetFragment = StatusBottomSheetFragment()
                statusSheetFragment.show(childFragmentManager, statusSheetFragment.tag)
            },
            onItemFavClickListener = { id, isChecked ->
                Timber.e("isChecked: $isChecked")
                homeViewModel.updateFavorite(id, isChecked)
            }
        )
        binding.recyclerTodo.adapter = adapter
        binding.recyclerTodo.itemAnimator = null
        binding.recyclerTodo.addItemDecoration(SpacesItemDecoration(8))
    }
}