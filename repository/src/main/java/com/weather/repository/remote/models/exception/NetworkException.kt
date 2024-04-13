package com.weather.repository.remote.models.exception

import com.google.gson.Gson
import okhttp3.ResponseBody
import java.io.IOException

class NetworkException(message: String) : IOException(message)

data class MoviesError(override val message: String) : Exception(message)

fun ResponseBody?.toMoviesError(): MoviesError {
    return if (this == null) {
        MoviesError("Error with empty response body")
    } else {
        val errorBodyStr = this.string()
        Gson().fromJson(errorBodyStr, MoviesError::class.java)
    }
}
