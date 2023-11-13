package com.govin.wisata;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.govin.wisata.databinding.ActivityMain2Binding;
import com.govin.wisata.databinding.ActivityMainBinding;


public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
        }

        setSupportActionBar(binding.toolbar);
        setTitle("Kelola Wisata");

        // Inisialisasi NavHostFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();

        // Inisialisasi AppBarConfiguration
        AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(
                R.id.homeFragment,
                R.id.favoriteFragment,
                R.id.ticketFragment,
                R.id.profileFragment
        ).build();

        // Menghubungkan ActionBar dengan NavController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);

        // Menghubungkan BottomNavigationView dengan NavController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}