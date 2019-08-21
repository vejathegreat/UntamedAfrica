package com.velaphi.untamed.features.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.navigation.NavigationView;
import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.features.about.AboutUsFragment;
import com.velaphi.untamed.features.categories.CategoriesFragment;
import com.velaphi.untamed.features.getInvolved.GetInvolvedFragment;
import com.velaphi.untamed.features.licenses.OpenSourceLicensesFragment;
import com.velaphi.untamed.features.safaries.SafarisFragment;
import com.velaphi.untamed.injection.UntamedFactory;
import com.velaphi.untamed.utils.FragmentBackPressed;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationDrawerViewModel navigationViewModelNavigation;

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
        NavigationView navigationView = findViewById(R.id.nav_view);
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

        navigationViewModelNavigation.categoryListScreenTrigger.observe(this, (aVoid -> openScreen(new CategoriesFragment())));
        navigationViewModelNavigation.licenceListScreenTrigger.observe(this, (aVoid -> openScreen(new OpenSourceLicensesFragment())));
        navigationViewModelNavigation.safarisScreenTrigger.observe(this, (aVoid -> openScreen(new SafarisFragment())));
        navigationViewModelNavigation.getInvolvedScreenTrigger.observe(this, (aVoid -> openScreen(new GetInvolvedFragment())));
        navigationViewModelNavigation.aboutUsScreenTrigger.observe(this, (aVoid -> openScreen(new AboutUsFragment())));
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
            navigationViewModelNavigation.openAboutUs();
        } else if (id == R.id.nav_safaris) {
            navigationViewModelNavigation.openSafaris();
        } else if (id == R.id.nav_get_involved) {
            navigationViewModelNavigation.openGetInvolved();
        } else if (id == R.id.nav_contributors) {
            navigationViewModelNavigation.openLicenses();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openScreen(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }

    private void openDefaultFragment() {
        navigationViewModelNavigation.openCategories();
    }


    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (!(fragment instanceof FragmentBackPressed) || !((FragmentBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }
}

