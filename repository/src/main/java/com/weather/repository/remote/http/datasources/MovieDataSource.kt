package com.weather.repository.remote.http.datasources

import com.weather.entities.entities.ObjectResult
import com.weather.entities.entities.Movie
import com.weather.repository.remote.http.interfaces.IMovieDataSource
import com.weather.repository.remote.http.models.responses.toMoviesResponse
import com.weather.repository.remote.http.services.MovieService
import com.weather.repository.utils.DynamicProperties.VALUE_API_KEY
import com.weather.repository.utils.retryIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource(private val movieService: MovieService) : IMovieDataSource {
    override suspend fun getMovies(): ObjectResult<List<Movie>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retryIO { this@MovieDataSource.movieService.getMovies(VALUE_API_KEY) }
                if (!response.isSuccessful) {
                    ObjectResult.Failure(Exception(response.errorBody().toString()))
                } else {
                    ObjectResult.Success(
                        response.body()?.results!!.toMoviesResponse()
                    )
                }
            } catch (ex: Exception) {
                ObjectResult.Failure(ex)
            }
        }
    }

    /*override suspend fun getCityByLatLon(
        lat: String,
        lon: String,
        apikey: String
    ): ObjectResult<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retryIO {
                    this@MovieDataSource.weatherService.getCityByLatLon(lat, lon, apikey)
                }
                if (!response.isSuccessful) {
                    ObjectResult.Failure(Exception(response.errorBody()?.toString()))
                } else {
                    ObjectResult.Success(response.body()?.toWeatherResponse()!!)
                }
            } catch (ex: Exception) {
                ObjectResult.Failure(ex)
            }
        }
    }*/
}
