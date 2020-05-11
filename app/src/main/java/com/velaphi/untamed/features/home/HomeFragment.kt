package com.velaphi.untamed.features.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.velaphi.untamed.R
import com.velaphi.untamed.UntamedAfricaApp
import com.velaphi.untamed.injection.UntamedFactory


class HomeFragment : Fragment() {

    private lateinit var chipGroup: ChipGroup
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var filters: List<Filter>
    private lateinit var filterItem: Filter
    private var defaultCheck: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        setupViews(view)
        setupViewModel()
        homeViewModel.retrieveListOfFilterItems()
//        setRefreshButtonOnClickListener(view)
//        aboutAppViewModel.retrieveAboutInformation()
        return view
    }

    private fun setupViews(view: View) {
        chipGroup = view.findViewById(R.id.filter_chip_group)
    }

    private fun setupViewModel() {
        val untamedComponentFactory = UntamedFactory(activity?.application as UntamedAfricaApp)
        homeViewModel = ViewModelProviders.of(this, untamedComponentFactory).get<HomeViewModel>(HomeViewModel::class.java)
        observeListOfFilters()
        observeExceptionMessage()
    }

    private fun observeExceptionMessage() {
        homeViewModel.getExceptionMessage().observe(viewLifecycleOwner, Observer {
            if (it != null) {
//                speakers_progressbar.visibility = View.GONE
//                user_error_information_textView.visibility = View.VISIBLE
//                speakers_refresh_button.visibility = View.VISIBLE
//                user_error_information_textView.text = "${it.message}"
            }
        })

    }

    private fun observeListOfFilters() {
        homeViewModel.getFilterItemMutableList().observe(viewLifecycleOwner, Observer<List<Filter>> {
            if (it != null) {
                filters = it
                displayFilters()
            }
        })
    }

    private fun displayFilters() {
        for (filter in filters) {
            addFilterChip(filter)
        }
    }

    private fun addFilterChip(filter: Filter) {
        val chip = Chip(activity)
        val paddingDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8f,
                resources.displayMetrics).toInt()
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
        chip.text = filter.name
        chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.grey))
        chip.isCheckable = true
        chip.checkedIcon = resources.getDrawable(R.drawable.ic_selected)

        if (filter.key.equals(DEFAULT_FILTER)) {
            chip.isChecked = true
            defaultCheck = true
        }

        chip.setOnClickListener {
            filterItem = filter
            filterData(filter)
            if (defaultCheck) {
                chipGroup.clearCheck()
                chip.isChecked = true

            }
        }
        chipGroup.addView(chip)
    }

    private fun filterData(filter: Filter) {
        Toast.makeText(activity, filter.name, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val DEFAULT_FILTER = "categories"
    }

}