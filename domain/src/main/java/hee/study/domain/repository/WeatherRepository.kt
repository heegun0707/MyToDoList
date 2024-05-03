package hee.study.domain.repository

import hee.study.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getTemperature(lat: String, lon: String): Flow<Weather>
}