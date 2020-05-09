package com.velaphi.untamed.features.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.velaphi.untamed.R
import com.velaphi.untamed.UntamedAfricaApp
import com.velaphi.untamed.features.favorites.FavoriteFragment
import com.velaphi.untamed.features.home.HomeFragment
import com.velaphi.untamed.injection.UntamedFactory

class MainBottomNavActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigationViewModelNavigation: NavigationDrawerViewModel
    private lateinit var bottomNavigationBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bottom_nav)
        setupViewModel()
        bottomNavigationBar = findViewById(R.id.bottom_navigation)
        bottomNavigationBar.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            openDefaultFragment()
        }
    }


    private fun openDefaultFragment() {
        navigationViewModelNavigation.openHome()
    }

    private fun setupViewModel() {
        val application = this.application as UntamedAfricaApp
        navigationViewModelNavigation = ViewModelProviders.of(this, UntamedFactory(application))
                .get(NavigationDrawerViewModel::class.java)
        navigationViewModelNavigation.homeScreenTrigger.observe(this, Observer {
            openScreen(HomeFragment())})

        navigationViewModelNavigation.favoritesScreenTrigger.observe(this, Observer { openScreen(FavoriteFragment())})
    }

    private fun openScreen(fragment: Fragment?) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment!!)
                .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                navigationViewModelNavigation.openHome()
                return true
            }
            R.id.navigation_animals -> {

                return true
            }
            R.id.navigation_favourites -> {
                navigationViewModelNavigation.openFavorites()
                return true
            }
            R.id.navigation_safaris -> {

                return true
            }
            R.id.navigation_more -> {

                return true
            }
        }
        return false
    }
}
