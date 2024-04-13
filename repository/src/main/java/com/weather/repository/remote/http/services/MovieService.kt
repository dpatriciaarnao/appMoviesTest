package com.weather.repository.remote.http.services

import com.weather.repository.remote.http.models.responses.MoviesDataResponse
import com.weather.repository.remote.http.models.responses.MoviesResponse
import com.weather.repository.utils.DynamicProperties
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(DynamicProperties.DEFAULT_BASE_URL+DynamicProperties.url)
    suspend fun getMovies(
        @Query(DynamicProperties.API_KEY) api_key: String
    ): Response<MoviesDataResponse>
}
