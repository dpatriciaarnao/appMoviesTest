package com.weather.repository.remote.http.services

import com.weather.repository.remote.http.models.responses.WeatherResponse
import com.weather.repository.utils.DynamicProperties
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(DynamicProperties.DEFAULT_BASE_URL)
    suspend fun getWeather(
        @Query("q") q: String?,
        @Query("appid") appid: String?,
    ): Response<WeatherResponse>

    @GET(DynamicProperties.DEFAULT_BASE_URL)
    suspend fun getCityByLatLon(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appid: String?,
    ): Response<WeatherResponse>
}
