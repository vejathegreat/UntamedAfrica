package com.velaphi.untamed.features.old_animalDetails

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.velaphi.untamed.R
import com.velaphi.untamed.features.old_animalDetails.adapters.ImagesAdapter
import kotlinx.android.synthetic.main.activity_all_images.*
import java.util.*

class AllImagesActivity : AppCompatActivity() {

    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_images)
        val intent = intent
        val imageList = intent.getStringArrayListExtra(ALL_IMAGES)

        setupToolbar()
        populateRecyclerView(imageList)
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.images)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun populateRecyclerView(imageList: ArrayList<String>?) {

        val orientation: Int = resources.configuration.orientation
        imagesAdapter = ImagesAdapter(this, false)

        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            images_recyclerview.layoutManager = GridLayoutManager(this, PORTRAIT_SPAN_COUNT)
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            images_recyclerview.layoutManager = GridLayoutManager(this, LANDSCAPE_SPAN_COUNT)

        images_recyclerview.itemAnimator = DefaultItemAnimator()
        images_recyclerview.adapter = imagesAdapter

        if (!imageList.isNullOrEmpty()) {
            imagesAdapter.setItems(imageList)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val ALL_IMAGES: String = "ALL_IMAGES"
        private const val PORTRAIT_SPAN_COUNT: Int = 2
        private const val LANDSCAPE_SPAN_COUNT: Int = 3
    }
}
