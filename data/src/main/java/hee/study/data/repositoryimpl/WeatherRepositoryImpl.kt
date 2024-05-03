package hee.study.data.repositoryimpl

import hee.study.data.remote.WeatherService
import hee.study.domain.model.Weather
import hee.study.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService
) : WeatherRepository {
    override suspend fun getTemperature(lat: String, lon: String): Flow<Weather> = flow {
        emit(service.getCurrentWeather(lat, lon, "metric", OPEN_WEATHER_KEY))
    }.flowOn(Dispatchers.IO).map {
        Timber.e("weather : $it")
        Weather(
            temp = it.main.temp,
            temp_min = it.main.temp_min,
            temp_max = it.main.temp_max
        )
    }

    companion object {
        private const val OPEN_WEATHER_KEY = "d117440655b12b3dbe9f9c264f64a0d5"
    }
}