package hee.study.todo.ui.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hee.study.domain.model.TodoItem
import hee.study.domain.utils.TodoStatus
import hee.study.todo.R
import hee.study.todo.base.BaseFragment
import hee.study.todo.databinding.FragmentHomeBinding
import hee.study.todo.ui.adapter.TodoListAdapter
import timber.log.Timber
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val homeViewModel: HomeViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var adapter: TodoListAdapter

    override fun initView() {
        super.initView()
        binding.homeVm = homeViewModel
        binding.weatherVm = weatherViewModel
        initAdapter()
        binding.tvAdvice.setOnClickListener {
            val today = LocalDate.now()

            homeViewModel.insertTodo(TodoItem(
                title = "title title title",
                memo = "memo memo memo",
                endDate = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                status = TodoStatus.BEFORE,
                isFavorite = false
            ))
        }
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
        adapter = TodoListAdapter {
            Timber.d("title : $it")
        }

        binding.recyclerTodo.apply {
            adapter = adapter
            addItemDecoration(SpacesItemDecoration(8))
            itemAnimator = null
        }
    }
}