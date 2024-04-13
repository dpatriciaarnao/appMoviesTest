package com.weather.presentation.views.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.weather.presentation.views.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class MovieFragment : Fragment() {
    private val rootViewModel by viewModels<MovieViewModel>()

    fun getMovieActivity(): MovieActivity = requireActivity() as MovieActivity

}
