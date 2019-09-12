package com.velaphi.untamed.features.animalDetails

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.velaphi.untamed.R
import com.velaphi.untamed.features.animalDetails.adapters.VideosAdapter
import com.velaphi.untamed.features.animalDetails.models.Video
import kotlinx.android.synthetic.main.activity_all_images.*
import java.util.*

class AllImagesActivity : AppCompatActivity() {

    private lateinit var videosAdapter: VideosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_images)
        val intent = intent
        val videoList = intent.getParcelableArrayListExtra<Video>(ALL_IMAGES)

        setupToolbar()
        populateRecyclerView(videoList)
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.videos)
        setSupportActionBar(toolbar)
    }

    private fun populateRecyclerView(videoList: ArrayList<Video>?) {

        val orientation: Int = resources.configuration.orientation
        videosAdapter = VideosAdapter(this, videoList, false)

        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            video_recyclerview.layoutManager = LinearLayoutManager(this)
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            video_recyclerview.layoutManager = GridLayoutManager(this, LANDSCAPE_SPAN_COUNT)

        video_recyclerview.itemAnimator = DefaultItemAnimator()
        video_recyclerview.adapter = videosAdapter
    }

    companion object {
        private const val ALL_IMAGES: String = "ALL_IMAGES"
        private const val PORTRAIT_SPAN_COUNT: Int = 2
        private const val LANDSCAPE_SPAN_COUNT: Int = 3
    }
}
