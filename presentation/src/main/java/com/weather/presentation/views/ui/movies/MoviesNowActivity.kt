package com.weather.presentation.views.ui.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.weather.entities.entities.Movie
import com.weather.presentation.views.adapters.MoviesAdapterList
import com.weather.presentation.views.base.MovieActivity
import com.weather.presentation.views.viewmodels.MovieViewModel
import com.weather.testweather.presentation.R
import com.weather.testweather.presentation.databinding.ActivityMoviesNowBinding
import kotlinx.coroutines.launch


class MoviesNowActivity : MovieActivity() {

    private lateinit var binding: ActivityMoviesNowBinding
    private val rootViewModel: MovieViewModel by viewModels()
    private var responseMovie: List<Movie> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_now)
        binding.lifecycleOwner = this

        initialize()
    }

    fun initialize() {
        lifecycle.coroutineScope.launch(){
            rootViewModel.loadDataMoviesNow()
        }

        rootViewModel.movieData.observe(this, Observer {
            val adapter = MoviesAdapterList(it!!)
            responseMovie = it!!
            /*binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewMovies.addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
            )*/
            //adapter.submitList(it)
            binding.recyclerViewMovies.adapter = adapter
            //binding.recyclerViewMovies.adapter = it
        })
    }


}
