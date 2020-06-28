package com.velaphi.untamed.features.old_animalDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.velaphi.untamed.R
import java.util.*


class ImageSliderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)
    }

    fun getIntent(context: Context, imageList: List<String>, position: Int): Intent {

        val intent = Intent(context, ImageSliderActivity::class.java)
        intent.putStringArrayListExtra(EXTRA_IMAGE_LIST, imageList as ArrayList<String>?)
        intent.putExtra(EXTRA_IMAGE_POSITION, position)
        return intent
    }

    companion object{
        const val EXTRA_IMAGE_LIST = "EXTRA_IMAGE_LIST"
        const val EXTRA_IMAGE_POSITION = "EXTRA_IMAGE_POSITION"
    }
}
