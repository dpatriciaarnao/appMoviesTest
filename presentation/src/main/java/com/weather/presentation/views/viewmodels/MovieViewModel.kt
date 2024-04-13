package com.weather.presentation.views.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.entities.entities.ObjectResult
import com.weather.entities.entities.Movie
import com.weather.repository.remote.http.models.responses.MoviesResponse
import com.weather.usecases.usecases.http.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<MovieViewState>()
    val viewState: LiveData<MovieViewState> = _viewState

    private val _movieData = MutableLiveData<List<Movie>?>()
    val movieData: MutableLiveData<List<Movie>?> = _movieData

    private val _movieCityData = MutableLiveData<Movie>()
    val movieCityData: LiveData<Movie> = _movieCityData

    fun loadDataMoviesNow() {
        viewModelScope.launch {
            when (val result = movieUseCase.getMovies()) {
                is ObjectResult.Success -> {
                    _viewState.value = MovieViewState.LoadData.Success(
                        movies = result.data
                    )
                    _movieData.value = result.data
                }
                is ObjectResult.Failure -> {
                    _viewState.value = MovieViewState.LoadData.Failure(result.exception)
                }
                else -> {}
            }
        }
    }

    /*fun loadDataCityByLatLon(lat: String, lon: String, apikey: String) {
        viewModelScope.launch {
            when (val result = movieUseCase.getCityByLatLon(lat, lon, apikey)) {
                is ObjectResult.Success -> {
                    _viewState.value = MovieByIdViewState.LoadData.Success(
                        movie = result.data
                    )
                    _weatherCityData.value = result.data
                }
                is ObjectResult.Failure -> {
                    _viewState.value = MovieByIdViewState.LoadData.Failure(result.exception)
                }
                else -> {}
            }
        }
    }*/



}

sealed class MovieByIdViewState {
    sealed class LoadData : MovieByIdViewState() {
        object Processing : LoadData()
        data class Success(val movie: Movie) : LoadData()
        data class Failure(val error: Throwable) : LoadData()
    }
}

sealed class MovieViewState {
    sealed class LoadData : MovieViewState() {
        object Processing : LoadData()
        data class Success(val movies: List<Movie>) : LoadData()
        data class Failure(val error: Throwable) : LoadData()
    }
}


