package com.weather.presentation.views.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.weather.presentation.views.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class WeatherFragment : Fragment() {
    private val rootViewModel by viewModels<WeatherViewModel>()

    fun getWeatherActivity(): WeatherActivity = requireActivity() as WeatherActivity

}
