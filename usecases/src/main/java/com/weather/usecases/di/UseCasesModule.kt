package com.weather.usecases.di

import android.content.Context
import com.weather.repository.remote.http.interfaces.IMovieDataSource
import com.weather.usecases.usecases.http.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Singleton
    @Provides
    fun provideMovieUseCase(
        @ApplicationContext context: Context,
        movieDataSource: IMovieDataSource
    ): MovieUseCase = MovieUseCase(
        context,
        movieDataSource
    )
}
