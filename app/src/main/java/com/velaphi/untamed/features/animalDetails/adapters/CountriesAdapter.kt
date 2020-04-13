package com.velaphi.untamed.features.animalDetails.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.velaphi.untamed.R
import com.velaphi.untamed.features.animalDetails.models.Country
import com.velaphi.untamed.injection.GlideApp
import com.velaphi.untamed.utils.AppUtil
import kotlinx.android.synthetic.main.country_list_item_layout.view.*


class CountriesAdapter(val countryList: List<Country>) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_list_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country: Country = countryList[position]
        holder.countryNameTextView.text = country.name

        val options = RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)

        GlideApp.with(holder.itemView.context)
                .load(AppUtil.getImageFromStorage(country.flag))
                .apply(options)
                .centerCrop()
                .into(holder.countryFlagImageView)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryFlagImageView = view.flag_imageview
        val countryNameTextView = view.country_name_textView
    }
}
