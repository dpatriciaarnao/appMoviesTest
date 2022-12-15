package com.weather.usecases.usecases.http

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.weather.entities.entities.ObjectResult
import com.weather.entities.entities.WeatherObject
import com.weather.repository.remote.http.interfaces.IWeatherDataSource
import com.weather.repository.remote.models.exception.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext

class WeatherUseCase(
    @ApplicationContext private val context: Context,
    private val weatherDataSource: IWeatherDataSource
) {

    private var _result = MutableLiveData<Any>()
    var result: MutableLiveData<Any> = _result

    suspend fun getWeatherByCity(q: String, apikey: String): ObjectResult<WeatherObject> {
        return try {
            val result = weatherDataSource.getWeather(q, apikey)
            if (result is ObjectResult.Failure && result.exception is NetworkException) {
                return result
            } else if (result is ObjectResult.Success) {
                _result.value = result
            }
            return result
        } catch (e: java.lang.Exception) {
            ObjectResult.Failure(e)
        }
    }
}
