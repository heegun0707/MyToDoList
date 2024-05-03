package hee.study.domain.usecase

import hee.study.domain.model.Weather
import hee.study.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTemperatureUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend fun execute(lat: String, lon: String): Flow<Weather> = repository.getTemperature(lat, lon)
}