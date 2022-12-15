package com.weather.repository.remote.http.datasources

import com.weather.entities.entities.ObjectResult
import com.weather.entities.entities.WeatherObject
import com.weather.repository.remote.http.interfaces.IWeatherDataSource
import com.weather.repository.remote.http.services.WeatherService
import com.weather.repository.utils.retryIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherDataSource(private val weatherService: WeatherService) : IWeatherDataSource {
    override suspend fun getWeather(q: String, apikey: String): ObjectResult<WeatherObject> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retryIO {
                    this@WeatherDataSource.weatherService.getWeather(q, apikey)
                }
                if (!response.isSuccessful) {
                    ObjectResult.Failure(Exception(response.errorBody()?.toString()))
                } else {
                    ObjectResult.Success(response.body()?.toWeatherResponse()!!)
                }
            } catch (ex: Exception) {
                ObjectResult.Failure(ex)
            }
        }
    }
}
