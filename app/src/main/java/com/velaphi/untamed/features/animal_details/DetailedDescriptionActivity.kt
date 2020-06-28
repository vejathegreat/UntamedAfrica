package com.velaphi.untamed.features.animal_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.velaphi.untamed.R
import com.velaphi.untamed.features.all_animals.models.Description
import com.velaphi.untamed.utils.Constants.ANIMAL_DESCRIPTION
import com.velaphi.untamed.utils.Constants.ANIMAL_NAME
import com.velaphi.untamed.utils.Constants.PARCEL
import kotlinx.android.synthetic.main.activity_detailed_description.*


class DetailedDescriptionActivity : AppCompatActivity() {
    private lateinit var description: Description
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_description)
        getData()
        populateContent()
        setupToolbar()
    }

    private fun populateContent() {
        title_textView.text = description.title
        content_textView.text = description.content
        source_textView.text = Html.fromHtml(getString(R.string.source, description.source))
        source_textView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(description.link)
            startActivity(intent)
        }

    }

    private fun getData() {
        val bundle = intent.getBundleExtra(PARCEL)
        description = bundle?.getParcelable(ANIMAL_DESCRIPTION)!!
        name = bundle.getString(ANIMAL_NAME)!!
    }

    private fun setupToolbar( ) {
       val toolbar: Toolbar? = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar?.title = name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}