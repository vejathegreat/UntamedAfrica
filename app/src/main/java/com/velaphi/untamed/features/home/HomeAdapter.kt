package com.velaphi.untamed.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.velaphi.untamed.R
import com.velaphi.untamed.injection.GlideApp
import com.velaphi.untamed.utils.AppUtil

class HomeAdapter( ) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var homeItemsList: ArrayList<HomeModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeItemsList.size
    }

    fun setItems(homeItemList: ArrayList<HomeModel>) {
        this.homeItemsList = homeItemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val homeItem: HomeModel? = homeItemsList[position]
        holder.name.text = homeItem?.name

        if (!homeItem?.description.isNullOrEmpty()) {
            holder.descriptions.visibility = VISIBLE
            holder.descriptions.text = homeItem?.description
        }

        val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)

        GlideApp.with(holder.itemView.context)
                .load(AppUtil.getImageFromStorage(homeItem?.image))
                .placeholder(R.color.colorAccent)
                .thumbnail(0.1f)
                .apply(options)
                .centerCrop()
                .into(holder.image)
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.home_image)
        val name: TextView = itemView.findViewById(R.id.home_name_textView)
        val descriptions: TextView = itemView.findViewById(R.id.home_description)
    }
}