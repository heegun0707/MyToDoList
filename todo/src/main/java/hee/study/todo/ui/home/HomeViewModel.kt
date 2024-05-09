package hee.study.todo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hee.study.domain.model.TodoItem
import hee.study.domain.usecase.*
import hee.study.domain.utils.TodoStatus
import hee.study.todo.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodoListUseCase: GetTodoListUseCase,
    private val getTodoCountUseCase: GetTodoCountUseCase,
    private val getAdviceUseCase: GetAdviceUseCase,
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoFavoriteUseCase: UpdateTodoFavoriteUseCase,
    private val updateTodoStatusUseCase: UpdateTodoStatusUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : BaseViewModel() {
    private val _toDoList = MutableLiveData<List<TodoItem>>(/*emptyList()*/)
    val toDoList: LiveData<List<TodoItem>> get() = _toDoList

    private val _toDoPercent = MutableLiveData<String>()
    val toDoPercent: LiveData<String> get() = _toDoPercent

    private val _toDoCount = MutableLiveData<Pair<Int, Int>>()
    val toDoCount: LiveData<Pair<Int, Int>> get() = _toDoCount

    private val _advice = MutableLiveData<String>()
    val advice: LiveData<String> get() = _advice

    private lateinit var selectedTodoItem: TodoItem

    init {
        getTodoList()
        getTodoCount()
        getAdvice()
    }

    private fun getTodoList() {
        viewModelScope.launch {
            getTodoListUseCase.execute()
                .onStart {
                    Timber.d("start get todo List")
                }.catch { throwable ->
                    Timber.e(
                        "Error occurred while fetching todo list: ${throwable.message}",
                        throwable
                    )
                }.collect {
                    Timber.d("Todo List : $it")
                    _toDoList.value = it
                }
        }
    }

    private fun getTodoCount() {
        viewModelScope.launch {
            getTodoCountUseCase.execute()
                .onStart {
                    Timber.d("start get todo total and completed count")
                }.catch { throwable ->
                    Timber.e(
                        "Error occurred while fetching todo count: ${throwable.message}",
                        throwable
                    )
                }.collect {
                    Timber.d("Total Count : ${it.first}, Completed Count : ${it.second}")
                    Timber.d("Percent : ${it.second.toDouble() / it.first.toDouble() * 100}")
                    _toDoPercent.value = if (it.first != 0) {
                        (it.second.toDouble() / it.first.toDouble() * 100).toInt().toString()
                    } else "0"
                    _toDoCount.value = it
                }
        }
    }

    private fun getAdvice() {
        viewModelScope.launch {
            getAdviceUseCase.execute()
                .catch { throwable ->
                    Timber.e(
                        "Error occurred while fetching advice: ${throwable.message}",
                        throwable
                    )
                }.collect {
                    Timber.d("advice : $it")
                    _advice.value = it.advice
                }
        }
    }

    fun insertTodo(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            insertTodoUseCase.execute(todoItem)
            getTodoCount()
        }
    }

    fun updateFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTodoFavoriteUseCase.execute(id, isFavorite)
            getTodoCount()
        }
    }

    fun updateStatus(id: Long, status: TodoStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTodoStatusUseCase.execute(id, status)
            getTodoCount()
        }
    }

    fun updateTodo(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTodoUseCase.execute(todoItem)
        }
    }

    fun deleteTodo(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTodoUseCase.execute(id)
            getTodoCount()
        }
    }

    fun setSelectedItem(item: TodoItem) {
        selectedTodoItem = item
    }

    fun getSelectedItem() = selectedTodoItem

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}