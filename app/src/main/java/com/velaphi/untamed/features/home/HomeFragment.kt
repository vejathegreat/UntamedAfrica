package com.velaphi.untamed.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.velaphi.untamed.R

class HomeFragment : Fragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
//        setupToolbar(view)
//        setupViews(view)
//        setupViewModel()
//        setRefreshButtonOnClickListener(view)
//        aboutAppViewModel.retrieveAboutInformation()
        return view
    }




}