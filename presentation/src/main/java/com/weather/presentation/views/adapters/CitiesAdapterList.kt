package com.weather.presentation.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weather.entities.entities.City
import com.weather.testweather.presentation.R
import com.weather.testweather.presentation.databinding.CitiesItemListBinding

class CitiesAdapterList(
    private val listener: CitiesAdapterListListener
) :
    ListAdapter<City, CitiesAdapterList.ViewHolder>(CitiesAdapterListDiffCallback()) {

    interface CitiesAdapterListListener {
        fun onClickDetail(cities: City)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cities_item_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setUi(item, listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CitiesItemListBinding.bind(itemView)

        fun setUi(
            citiesList: City,
            listener: CitiesAdapterListListener
        ) {
            val context = binding.root.context
            binding.tvCityName.text = citiesList.name

            binding.tvCityName.setOnClickListener {
                listener.onClickDetail(citiesList)
            }

        }
    }
}

class CitiesAdapterListDiffCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}
