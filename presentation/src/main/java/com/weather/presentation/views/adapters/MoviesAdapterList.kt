package com.weather.presentation.views.adapters

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weather.entities.entities.Movie
import com.weather.testweather.presentation.R
import com.weather.testweather.presentation.databinding.MovieDetailItemBinding

class MoviesAdapterList(private val moviesList: List<Movie>) : RecyclerView.Adapter<MoviesAdapterList.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_detail_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = moviesList[position]
        holder.itemViewBinding.tvTitle.text = item.original_title.toString() + "(Click here for more detail)"
        holder.itemViewBinding.tvDescription.text = item.overview.toString()
        Picasso.get().load(item.backdrop_path).fit().centerCrop(
            Gravity.TOP).into(holder.itemViewBinding.ivImageMovie)
        holder.itemViewBinding.constraintLayout.setOnClickListener {
            Log.d("itemClicked", item.toString())
            holder.itemViewBinding.tvDescription.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = moviesList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemViewBinding = MovieDetailItemBinding.bind(itemView)
    }
}
