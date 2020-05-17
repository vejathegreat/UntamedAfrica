package com.velaphi.untamed.features.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.velaphi.untamed.R
import com.velaphi.untamed.UntamedAfricaApp
import com.velaphi.untamed.injection.UntamedFactory


class HomeFragment : Fragment() {

    private lateinit var chipGroup: ChipGroup
    private lateinit var homeItemRecyclerView: RecyclerView
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var filterModels: List<FilterModel>
    private lateinit var filterModelItem: FilterModel
    private var defaultCheck: Boolean = false
    private lateinit var mShimmerViewContainerLinear: ShimmerFrameLayout
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        setupViews(view)
        setupViewModel()
        homeViewModel.retrieveListOfFilterItems()
        homeViewModel.retrieveListOfHomeItems(DEFAULT_FILTER)
        return view
    }

    private fun setupViews(view: View) {
        chipGroup = view.findViewById(R.id.filter_chip_group)
        mShimmerViewContainerLinear = view.findViewById(R.id.shimmer_view_container_linear)
        homeItemRecyclerView = view.findViewById(R.id.home_recyclerview)
    }

    private fun setupRecyclerview(homeItemList: ArrayList<HomeModel>) {
        homeAdapter = HomeAdapter()
        mShimmerViewContainerLinear.stopShimmerAnimation()
        mShimmerViewContainerLinear.visibility = GONE
        homeItemRecyclerView.layoutManager = LinearLayoutManager(activity)
        homeItemRecyclerView.itemAnimator = DefaultItemAnimator()
        homeItemRecyclerView.adapter = homeAdapter
        homeAdapter.setItems(homeItemList)
    }


    private fun setupViewModel() {
        val untamedComponentFactory = UntamedFactory(activity?.application as UntamedAfricaApp)
        homeViewModel = ViewModelProviders.of(this, untamedComponentFactory).get<HomeViewModel>(HomeViewModel::class.java)
        observeListOfFilters()
        observeListOfHomeItems()
        observeExceptionMessage()
    }

    private fun observeListOfHomeItems() {
        homeViewModel.getHomeItemMutableList().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setupRecyclerview(it)
            }
        })
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
        homeViewModel.getFilterItemMutableList().observe(viewLifecycleOwner, Observer<List<FilterModel>> {
            if (it != null) {
                filterModels = it
                displayFilters()
            }
        })
    }

    private fun displayFilters() {
        for (filter in filterModels) {
            addFilterChip(filter)
        }
    }

    private fun addFilterChip(filterModel: FilterModel) {
        val chip = Chip(activity)
        val paddingDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8f,
                resources.displayMetrics).toInt()
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
        chip.text = filterModel.name
        chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.grey))
        chip.isCheckable = true
        chip.checkedIcon = resources.getDrawable(R.drawable.ic_pets)

        if (filterModel.key.equals(DEFAULT_FILTER)) {
            chip.isChecked = true
            defaultCheck = true
        }

        chip.setOnClickListener {
            filterModelItem = filterModel
            filterData(filterModel)
            if (defaultCheck) {
                chipGroup.clearCheck()
                chip.isChecked = true

            }
        }
        chipGroup.addView(chip)
    }

    private fun filterData(filterModel: FilterModel) {
        mShimmerViewContainerLinear.visibility = VISIBLE
        mShimmerViewContainerLinear.startShimmerAnimation()
        homeViewModel.retrieveListOfHomeItems(filterModel.key.toString())
    }

    override fun onResume() {
        super.onResume()
        mShimmerViewContainerLinear.startShimmerAnimation()
    }

    companion object {
        private const val DEFAULT_FILTER = "categories"
    }

}