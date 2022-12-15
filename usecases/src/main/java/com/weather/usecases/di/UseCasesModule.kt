package com.weather.usecases.di

import android.content.Context
import com.weather.repository.remote.http.interfaces.IWeatherDataSource
import com.weather.usecases.usecases.http.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Singleton
    @Provides
    fun provideWeatherUseCase(
        @ApplicationContext context: Context,
        weatherDataSource: IWeatherDataSource
    ): WeatherUseCase = WeatherUseCase(
        context,
        weatherDataSource
    )
}
