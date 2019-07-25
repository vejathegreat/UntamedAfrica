package com.velaphi.untamed.features.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.navigation.NavigationView;
import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.features.categories.CategoriesFragment;
import com.velaphi.untamed.injection.UntamedFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationDrawerViewModel navigationViewModelNavigation;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupViewModel();

        if (savedInstanceState == null) {
            openDefaultFragment();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupViewModel() {
        UntamedAfricaApp application = (UntamedAfricaApp) this.getApplication();
        navigationViewModelNavigation = ViewModelProviders.of(this, new UntamedFactory(application))
                .get(NavigationDrawerViewModel.class);

        navigationViewModelNavigation.categoryListScreenTrigger.observe(this, (new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                openScreen(new CategoriesFragment());
            }
        }));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_categories) {
            navigationViewModelNavigation.openCategories();
        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_announcement) {

        } else if (id == R.id.nav_news) {

        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_safaris) {

        } else if (id == R.id.nav_get_involved) {

        } else if (id == R.id.nav_contributors) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openScreen(CategoriesFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }

    private void openDefaultFragment() {
        navigationViewModelNavigation.openCategories();
    }


}

