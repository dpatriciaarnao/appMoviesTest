package com.weather.presentation.views.ui.weather

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.weather.presentation.views.base.WeatherFragment
import com.weather.presentation.views.viewmodels.WeatherViewModel
import com.weather.repository.utils.DynamicProperties
import com.weather.testweather.presentation.databinding.FragmentWeatherDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class WeatherDetailFragment : WeatherFragment() {

    private lateinit var binding: FragmentWeatherDetailBinding
    private val rootViewModel: WeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.fragmentManager?.popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        initVariables()
        return binding.root
    }

    private fun initVariables() {
        val city = arguments?.getString(CITY_SELECTED)
        consumeService(city ?:"Buenos Aires", DynamicProperties.API_KEY)
        rootViewModel.weatherData.observe(viewLifecycleOwner) {
            binding.inclWeatherDetail.tvTempActual.text = it.main?.temp.toString()
            binding.inclWeatherDetail.tvTempMinima.text = it.main?.temp_min.toString()
            binding.inclWeatherDetail.tvTempMaxima.text = it.main?.temp_max.toString()
            binding.inclWeatherDetail.tvVientos.text = it.wind?.speed.toString()
        }

        val gcd = Geocoder(context, Locale.getDefault())
        val addresses: List<Address> = gcd.getFromLocation(lat, lng, 1)
        if (addresses.size > 0) {
            System.out.println(addresses[0].getLocality())
        } else {
            // do your stuff
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inclWeatherDetail.ivBackbutton.setOnClickListener{
            parentFragmentManager.popBackStack()
            requireActivity().onBackPressed()
        }
    }

    private fun consumeService(q: String, apikey: String) {
        rootViewModel.loadDataWeatherByCity(q, apikey)
    }



    companion object {
        private const val CITY_SELECTED = "citySelected"
        val TAG = WeatherDetailFragment::javaClass.name
        fun newInstance(citySelected: String): WeatherDetailFragment =
            WeatherDetailFragment().also {
                it.arguments = Bundle().also { b -> b.putString(CITY_SELECTED, citySelected) }
            }
        }
}
