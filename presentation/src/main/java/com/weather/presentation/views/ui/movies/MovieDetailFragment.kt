package com.weather.presentation.views.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.weather.presentation.views.base.MovieFragment
import com.weather.presentation.views.viewmodels.MovieViewModel
import com.weather.repository.utils.DynamicProperties
import com.weather.testweather.presentation.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : MovieFragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val rootViewModel: MovieViewModel by activityViewModels()

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
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        initVariables()
        return binding.root
    }

    private fun initVariables() {
        val city = arguments?.getString(CITY_SELECTED)

        rootViewModel.movieData.observe(viewLifecycleOwner) {

        }

        /*val gcd = Geocoder(context, Locale.getDefault())
        val addresses: List<Address> = gcd.getFromLocation(lat, lng, 1)
        if (addresses.size > 0) {
            System.out.println(addresses[0].getLocality())
        } else {
            // do your stuff
        }*/

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        private const val CITY_SELECTED = "citySelected"
        val TAG = MovieDetailFragment::javaClass.name
        fun newInstance(citySelected: String): MovieDetailFragment =
            MovieDetailFragment().also {
                it.arguments = Bundle().also { b -> b.putString(CITY_SELECTED, citySelected) }
            }
        }
}
