package com.velaphi.untamed.features.all_animals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.velaphi.untamed.R
import com.velaphi.untamed.UntamedAfricaApp
import com.velaphi.untamed.features.all_animals.models.AnimalModel
import com.velaphi.untamed.injection.UntamedFactory
import java.util.*

class AllAnimalsFragment : Fragment()  {

    private lateinit var animalsItemRecyclerView: RecyclerView
    private lateinit var animalsViewModel: AnimalsViewModel
    private lateinit var animalsAdapter: AllAnimalsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_animals, container, false)
        setupViews(view)
        setupToolbar(view)
        setupViewModel()
        animalsViewModel.retrieveListOfAnimalsItems()
        return view
    }

    private fun setupViews(view: View) {
        animalsItemRecyclerView = view.findViewById(R.id.animals_recyclerview)
    }

    private fun setupToolbar(view: View?) {
        val toolbar: Toolbar? = view?.findViewById(R.id.toolbar)
        toolbar?.title = activity?.getString(R.string.default_title)
    }

    private fun setupViewModel() {
        val untamedComponentFactory = UntamedFactory(activity?.application as UntamedAfricaApp)
        animalsViewModel = ViewModelProviders.of(this, untamedComponentFactory).get(AnimalsViewModel::class.java)
        observeListOfAllAnimalsItems()
        observeExceptionMessage()
    }

    private fun observeExceptionMessage() {
        animalsViewModel.getExceptionMessage().observe(viewLifecycleOwner, Observer {
            if (it != null) {
//                speakers_progressbar.visibility = View.GONE
//                user_error_information_textView.visibility = View.VISIBLE
//                speakers_refresh_button.visibility = View.VISIBLE
//                user_error_information_textView.text = "${it.message}"
            }
        })
    }

    private fun observeListOfAllAnimalsItems() {
        animalsViewModel.getHAnimalsItemMutableList().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setupRecyclerview(it)
            }
        })
    }

    private fun setupRecyclerview(it: ArrayList<AnimalModel>) {
        animalsAdapter = AllAnimalsAdapter()
//        mShimmerViewContainerLinear.stopShimmerAnimation()
//        mShimmerViewContainerLinear.visibility = View.GONE
        animalsItemRecyclerView.layoutManager = LinearLayoutManager(activity)
        animalsItemRecyclerView.itemAnimator = DefaultItemAnimator()
        animalsItemRecyclerView.adapter = animalsAdapter
        animalsAdapter.setItems(it)
    }
}