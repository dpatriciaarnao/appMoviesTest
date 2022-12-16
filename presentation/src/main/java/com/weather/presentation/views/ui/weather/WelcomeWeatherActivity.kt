package com.weather.presentation.views.ui.weather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.weather.entities.entities.City
import com.weather.presentation.views.adapters.CitiesAdapterList
import com.weather.presentation.views.base.WeatherActivity
import com.weather.presentation.views.viewmodels.WeatherViewModel
import com.weather.repository.utils.DynamicProperties
import com.weather.testweather.presentation.R
import com.weather.testweather.presentation.databinding.ActivityWeatherBinding


class WelcomeWeatherActivity : WeatherActivity(), LocationListener {

    private lateinit var binding: ActivityWeatherBinding
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    private val rootViewModel: WeatherViewModel by viewModels()
    private var latitude: String = ""
    private var longitude: String = ""
    private var city: String = ""

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

        location()
    }

    private fun location() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    fun initialize(city: String) {

        val itemsSectionTitle = mutableListOf(
            City("Montevideo"),
            City("Londres"),
            City("San Pablo"),
            City("Buenos Aires"),
            City("Munich")
        )

        itemsSectionTitle.add(City(city))

        cityListAdapter.submitList(itemsSectionTitle)
        binding.recyclerViewCities.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerViewCities.adapter = cityListAdapter
    }

    private fun consumeServiceByGps(latitude: String, longitude: String, apikey: String) {
        rootViewModel.loadDataCityByLatLon(latitude, longitude, apikey)
    }

    override fun onLocationChanged(p0: Location) {
        latitude = p0.latitude.toString()
        longitude = p0.longitude.toString()
        consumeServiceByGps(latitude, longitude, DynamicProperties.API_KEY)
        rootViewModel.weatherCityData.observe(this) {
            city = it.name!!
            binding.tvCurrentCity.text = "Usted se encuentra en $city"
            initialize(city)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
