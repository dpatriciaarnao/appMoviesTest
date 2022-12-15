package com.weather.repository.remote.models.exception

import com.google.gson.Gson
import okhttp3.ResponseBody
import java.io.IOException

class NetworkException(message: String) : IOException(message)

data class WeatherError(override val message: String) : Exception(message)

fun ResponseBody?.toWeatherError(): WeatherError {
    return if (this == null) {
        WeatherError("Error with empty response body")
    } else {
        val errorBodyStr = this.string()
        Gson().fromJson(errorBodyStr, WeatherError::class.java)
    }
}
