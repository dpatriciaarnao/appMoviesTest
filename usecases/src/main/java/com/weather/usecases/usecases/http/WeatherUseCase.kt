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

    private var _resultWeather = MutableLiveData<Any>()
    var resultWeather: MutableLiveData<Any> = _resultWeather

    private var _resultCity = MutableLiveData<Any>()
    var resultCity: MutableLiveData<Any> = _resultCity

    suspend fun getWeatherByCity(q: String, apikey: String): ObjectResult<WeatherObject> {
        return try {
            val result = weatherDataSource.getWeather(q, apikey)
            if (result is ObjectResult.Failure && result.exception is NetworkException) {
                return result
            } else if (result is ObjectResult.Success) {
                _resultWeather.value = result
            }
            return result
        } catch (e: java.lang.Exception) {
            ObjectResult.Failure(e)
        }
    }

    suspend fun getCityByLatLon(lat: String, lon: String, apikey: String): ObjectResult<WeatherObject> {
        return try {
            val result = weatherDataSource.getCityByLatLon(lat, lon, apikey)
            if (result is ObjectResult.Failure && result.exception is NetworkException) {
                return result
            } else if (result is ObjectResult.Success) {
                _resultCity.value = result
            }
            return result
        } catch (e: java.lang.Exception) {
            ObjectResult.Failure(e)
        }
    }
}
