package com.velaphi.untamed.features.animal_details

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.velaphi.untamed.R
import com.velaphi.untamed.features.all_animals.models.AnimalModel
import com.velaphi.untamed.injection.GlideApp
import com.velaphi.untamed.utils.AppUtil
import com.velaphi.untamed.utils.Constants.ANIMAL_DESCRIPTION
import com.velaphi.untamed.utils.Constants.ANIMAL_MODEL
import com.velaphi.untamed.utils.Constants.ANIMAL_NAME
import com.velaphi.untamed.utils.Constants.PARCEL
import kotlinx.android.synthetic.main.activity_animal_details.*
import kotlinx.android.synthetic.main.content_animal_details.*

class AnimalDetailsActivity : AppCompatActivity() {

    private lateinit var animalModel: AnimalModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_details)
        setSupportActionBar(findViewById(R.id.toolbar))
        getData()
        populateData()
        populateBanner()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun getData() {
        val bundle = intent.getBundleExtra(PARCEL)
        animalModel = bundle?.getParcelable(ANIMAL_MODEL)!!
    }

    private fun populateData() {
        description_title_textView.text = animalModel.description?.title
        description_content_textView.text = animalModel.description?.content
        tag_textView.text = animalModel.banner?.tag
        setupToolbar()

    }

    private fun setupToolbar() {
        val toolbar =  findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = animalModel.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun populateBanner() {
        val options = RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)

        GlideApp.with(this)
                .load(AppUtil.getImageFromStorage(animalModel.banner?.image))
                .apply(options)
                .centerCrop()
                .into(banner_imageView)

        read_more_button.setOnClickListener {
            val intent = Intent(this, DetailedDescriptionActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(ANIMAL_DESCRIPTION, animalModel.description)
            bundle.putString(ANIMAL_NAME, animalModel.name)
            intent.putExtra(PARCEL, bundle)
            this.startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}