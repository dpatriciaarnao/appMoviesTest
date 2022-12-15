package com.weather.presentation.views.ui.weather

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.weather.entities.entities.City
import com.weather.presentation.views.adapters.CitiesAdapterList
import com.weather.presentation.views.base.WeatherActivity
import com.weather.testweather.presentation.R
import com.weather.testweather.presentation.databinding.ActivityWeatherBinding


class WelcomeWeatherActivity : WeatherActivity() {

    private lateinit var binding: ActivityWeatherBinding

    private val cityListAdapter: CitiesAdapterList by lazy {
        CitiesAdapterList(
            listener = object : CitiesAdapterList.CitiesAdapterListListener{
                override fun onClickDetail(city: City) {
                    val citySelected = city.name
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.container, WeatherDetailFragment.newInstance(citySelected)).commit()
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        binding.lifecycleOwner = this

        initializeList()
    }

    fun initializeList() {

        val itemsSectionTitle = mutableListOf(
            City("Montevideo"),
            City("Londres"),
            City("San Pablo"),
            City("Buenos Aires"),
            City("Munich")
        )

        cityListAdapter.submitList(itemsSectionTitle)
        binding.recyclerViewCities.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerViewCities.adapter = cityListAdapter
    }
}
