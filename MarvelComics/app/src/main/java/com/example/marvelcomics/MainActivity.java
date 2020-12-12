package com.example.marvelcomics;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        setupBottomNavigationBar();
//        setUpNavigationDrawer(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupSearchView();
    }

    private void setupSearchView() {
        SearchView searchView = findViewById(R.id.search_bar);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_characters, R.id.navigation_creators)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
//
//    private void setUpNavigationDrawer(Toolbar toolbar) {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle drawerToggle =
//                new ActionBarDrawerToggle(this, drawer, toolbar,
//                        R.string.navigation_open,
//                        R.string.navigation_close);
//        drawer.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        switch (item.getItemId()) {
//            case R.id.nav_profile:
//                drawer.closeDrawer(GravityCompat.START);
//                displayToast("Profile");
//                return true;
//            case R.id.nav_share:
//                drawer.closeDrawer(GravityCompat.START);
//                displayToast("Share");
//                return true;
//            case R.id.nav_send:
//                drawer.closeDrawer(GravityCompat.START);
//                displayToast("Send");
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    private void displayToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }

}