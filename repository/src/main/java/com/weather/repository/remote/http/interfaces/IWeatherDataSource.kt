package com.weather.repository.remote.http.interfaces

import com.weather.entities.entities.ObjectResult
import com.weather.entities.entities.WeatherObject

interface IWeatherDataSource {
    suspend fun getWeather(q: String, apikey: String): ObjectResult<WeatherObject>
}
