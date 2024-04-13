package com.weather.repository.remote.http.models.responses

import com.weather.entities.entities.*

data class MoviesDataResponse(
    val page: Int,
    val results: List<MoviesResponse>,
    val total_pages: Int,
    val total_results: Int
)
data class MoviesResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: ArrayList<Int>?,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toEntity() = Movie(
        adult,
        backdrop_path,
        genre_ids,
        id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count
    )
}

fun List<MoviesResponse>.toMoviesResponse(): List<Movie> = this.map {
    it.toEntity()
}


