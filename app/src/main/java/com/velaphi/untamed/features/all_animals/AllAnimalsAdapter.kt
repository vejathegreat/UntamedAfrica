package com.velaphi.untamed.features.all_animals

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.velaphi.untamed.R
import com.velaphi.untamed.features.all_animals.models.AnimalModel
import com.velaphi.untamed.features.animal_details.AnimalDetailsActivity
import com.velaphi.untamed.injection.GlideApp
import com.velaphi.untamed.utils.AppUtil
import com.velaphi.untamed.utils.Constants.ANIMAL_MODEL
import com.velaphi.untamed.utils.Constants.PARCEL


class AllAnimalsAdapter : RecyclerView.Adapter<AllAnimalsAdapter.AllAnimalsViewHolder>() {

    private var animalsItemList: ArrayList<AnimalModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAnimalsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_all_animals, parent, false)
        return AllAnimalsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animalsItemList.size
    }

    fun setItems(animalsItemList: ArrayList<AnimalModel>) {
        this.animalsItemList = animalsItemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AllAnimalsViewHolder, position: Int) {
        val animalModel: AnimalModel? = animalsItemList[position]
        holder.name.text = animalModel?.name
        var context = holder.itemView.context

        val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)

        GlideApp.with(holder.itemView.context)
                .load(AppUtil.getImageFromStorage(animalModel?.banner?.image))
                .placeholder(R.color.shimmer)
                .thumbnail(0.1f)
                .apply(options)
                .centerCrop()
                .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(context ,AnimalDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(ANIMAL_MODEL, animalModel)
            intent.putExtra(PARCEL, bundle)
            context.startActivity(intent)
        }

    }

    class AllAnimalsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name_textView)
    }

}