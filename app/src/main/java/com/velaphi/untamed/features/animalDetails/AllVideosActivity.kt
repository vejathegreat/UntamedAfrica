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
import kotlinx.android.synthetic.main.activity_all_videos.*
import java.util.*


class AllVideosActivity : AppCompatActivity() {
    private lateinit var videosAdapter: VideosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_videos)
        val intent = intent
        val videoList = intent.getParcelableArrayListExtra<Video>(ALL_VIDEOS)

        setupToolbar()
        populateRecyclerView(videoList)
    }


    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.videos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun populateRecyclerView(videoList: ArrayList<Video>?) {
        val orientation: Int = resources.configuration.orientation
        videosAdapter = VideosAdapter(this, videoList, false)

        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            videos_recyclerview.layoutManager = LinearLayoutManager(this)
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            videos_recyclerview.layoutManager = GridLayoutManager(this, LANDSCAPE_SPAN_COUNT)

        videos_recyclerview.itemAnimator = DefaultItemAnimator()
        videos_recyclerview.adapter = videosAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val ALL_VIDEOS: String = "ALL_VIDEOS"
        private const val LANDSCAPE_SPAN_COUNT: Int = 2
    }
}
