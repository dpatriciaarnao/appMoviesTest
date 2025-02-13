package com.weather.presentation.views.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.weather.presentation.views.base.MovieActivity
import com.weather.presentation.views.ui.movies.MoviesNowActivity
import com.weather.testweather.presentation.R
import com.weather.testweather.presentation.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : MovieActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MoviesNowActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
