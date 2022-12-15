package com.weather.repository.remote.http.models.responses

import com.weather.entities.entities.*

data class WeatherResponse(
    val coord: Coord?,
    val weather: ArrayList<Weather>?,
    val base: String?,
    val main: Main?,
    val visibility: Int?,
    val wind: Wind?,
    val clouds: Clouds?,
    val dt: Int?,
    val sys: Sys?,
    var timezone: Int?,
    var id: Int?,
    var name: String?,
    var cod: Int?
){
    fun toWeatherResponse(): WeatherObject = WeatherObject(
        this.coord,
        this.weather,
        this.base,
        this.main,
        this.visibility,
        this.wind,
        this.clouds,
        this.dt,
        this.sys,
        this.timezone,
        this.id,
        this.name,
        this.cod
    )
}


