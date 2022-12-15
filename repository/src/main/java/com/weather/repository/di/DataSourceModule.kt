package com.weather.repository.di

import com.weather.repository.remote.http.datasources.WeatherDataSource
import com.weather.repository.remote.http.interfaces.IWeatherDataSource
import com.weather.repository.remote.http.services.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideWeatherDataSource(api: WeatherService): IWeatherDataSource = WeatherDataSource(api)
}
