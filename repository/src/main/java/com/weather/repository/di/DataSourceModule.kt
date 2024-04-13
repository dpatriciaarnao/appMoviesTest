package com.weather.repository.di

import com.weather.repository.remote.http.datasources.MovieDataSource
import com.weather.repository.remote.http.interfaces.IMovieDataSource
import com.weather.repository.remote.http.services.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMovieDataSource(api: MovieService): IMovieDataSource = MovieDataSource(api)
}
