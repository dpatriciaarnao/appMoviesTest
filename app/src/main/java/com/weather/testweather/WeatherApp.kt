package com.weather.testweather

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}
