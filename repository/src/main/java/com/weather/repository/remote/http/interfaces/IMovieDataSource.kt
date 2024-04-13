package com.weather.repository.remote.http.interfaces

import com.weather.entities.entities.ObjectResult
import com.weather.entities.entities.Movie
import com.weather.repository.remote.http.models.responses.MoviesResponse

interface IMovieDataSource {
    suspend fun getMovies(): ObjectResult<List<Movie>>
    //suspend fun getCityByLatLon(lat: String, lon: String, apikey: String): ObjectResult<Movie>
}
