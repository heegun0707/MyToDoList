package hee.study.todo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hee.study.domain.usecase.*
import hee.study.todo.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getTemperatureUseCase: GetTemperatureUseCase
) : BaseViewModel() {
    private val _currentTemp = MutableLiveData<String>()
    val temp: LiveData<String> get() = _currentTemp

    init {
        getCurrentTemp("44.13", "10.53")
    }

    private fun getCurrentTemp(lat: String, lon: String) {
        viewModelScope.launch {
            getTemperatureUseCase.execute(lat, lon)
                .onStart {
                    Timber.d("start get current temperature")
                }.catch { throwable ->
                    Timber.e(
                        "Error occurred while fetching temp: ${throwable.message}",
                        throwable
                    )
                }.collect {
                    Timber.d("current temp : $it")
                    _currentTemp.value = it.temp.toString()
                }
        }
    }
}