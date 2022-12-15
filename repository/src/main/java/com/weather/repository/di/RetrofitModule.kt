package com.weather.repository.di

import android.content.Context
import android.os.Build
import com.sweatworks.repository.remote.http.services.ApiPrefixes
import com.weather.repository.remote.http.interceptors.CacheInterceptor
import com.weather.repository.remote.http.interceptors.LoggingInterceptor
import com.weather.repository.remote.http.services.WeatherService
import com.weather.repository.utils.DynamicProperties
import com.weather.repository.utils.RepositoryConstants
import com.weather.testweather.repository.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(@ApplicationContext context: Context) = DynamicProperties.DEFAULT_BASE_URL

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Provides
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: LoggingInterceptor,
        cacheInterceptor: CacheInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(RepositoryConstants.CONNECTTIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RepositoryConstants.READWRITETIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RepositoryConstants.READWRITETIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(cacheInterceptor)


        if (BuildConfig.DEBUG)
            clientBuilder.addInterceptor(httpLoggingInterceptor)

        return clientBuilder.build()
    }

    @Provides
    fun provideCacheInterceptor(
        @ApplicationContext context: Context
    ): CacheInterceptor = CacheInterceptor(context)

    @Provides
    fun provideHttpLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor()
    }

    @Provides
    fun providerCache(@ApplicationContext context: Context): Cache {
        val cacheSize: Long = RepositoryConstants.CACHE_SIZE
        return Cache(context.cacheDir, cacheSize)
    }

}