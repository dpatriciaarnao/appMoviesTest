package com.weather.presentation.views.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.entities.entities.ObjectResult
import com.weather.entities.entities.WeatherObject
import com.weather.repository.remote.http.interfaces.IWeatherDataSource
import com.weather.repository.remote.http.models.responses.WeatherResponse
import com.weather.usecases.usecases.http.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherUseCase: WeatherUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<WeatherByCityViewState>()
    val viewState: LiveData<WeatherByCityViewState> = _viewState

    private val _weatherData = MutableLiveData<WeatherObject>()
    val weatherData: LiveData<WeatherObject> = _weatherData

    fun loadDataWeatherByCity(q: String, apikey: String) {
        viewModelScope.launch {
            when (val result = weatherUseCase.getWeatherByCity(q, apikey)) {
                is ObjectResult.Success -> {
                    _viewState.value = WeatherByCityViewState.LoadData.Success(
                        weatherObject = result.data
                    )
                    _weatherData.value = result.data
                }
                is ObjectResult.Failure -> {
                    _viewState.value = WeatherByCityViewState.LoadData.Failure(result.exception)
                }
                else -> {}
            }
        }
    }



}

sealed class WeatherByCityViewState {
    sealed class LoadData : WeatherByCityViewState() {
        object Processing : LoadData()
        data class Success(val weatherObject: WeatherObject) : LoadData()
        data class Failure(val error: Throwable) : LoadData()
    }
}


