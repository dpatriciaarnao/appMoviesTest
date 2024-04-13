package com.weather.usecases.usecases.http

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.weather.entities.entities.ObjectResult
import com.weather.entities.entities.Movie
import com.weather.repository.remote.http.interfaces.IMovieDataSource
import com.weather.repository.remote.http.models.responses.MoviesResponse
import com.weather.repository.remote.models.exception.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieUseCase(
    @ApplicationContext private val context: Context,
    private val movieDataSource: IMovieDataSource
) {

    //private var _resultMovie = MutableLiveData<List<Movie>>()
    //var resultMovie: MutableLiveData<List<Movie>> = _resultMovie

    private var _resultCity = MutableLiveData<Any>()
    var resultCity: MutableLiveData<Any> = _resultCity

    private val _resultMovie: MutableStateFlow<ObjectResult<List<Movie>>?> =
        MutableStateFlow(null)
    val workoutResult: StateFlow<ObjectResult<List<Movie>>?> = _resultMovie

    suspend fun getMovies(): ObjectResult<List<Movie>> {
        val response = this.movieDataSource.getMovies()
        if (response is ObjectResult.Failure && response.exception is NetworkException) {
            return response
        } else if (response is ObjectResult.Success) {
            _resultMovie.value = response
        }
        return response
    }

    /*suspend fun getCityByLatLon(lat: String, lon: String, apikey: String): ObjectResult<Movie> {
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
    }*/
}
