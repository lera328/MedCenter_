package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainSupport extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_support);

         BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation1);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.main_support);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_profile) {
                    startActivity(new Intent(getApplicationContext(), MainProfile.class));
                    overridePendingTransition(0, 0);return true;
                }
                if (item.getItemId() == R.id.main_res) {
                    startActivity(new Intent(getApplicationContext(), MainResult.class));
                    overridePendingTransition(0, 0);return true;
                }
                if (item.getItemId() == R.id.main_analis) {
                    startActivity(new Intent(getApplicationContext(), Main_analis.class));
                    overridePendingTransition(0, 0);return true;
                }
                return true;
            }
        });
    }
}