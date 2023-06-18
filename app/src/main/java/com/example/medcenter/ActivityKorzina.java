package com.example.medcenter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medcenter.ui.main.PreferencesManager;

public class ActivityKorzina extends AppCompatActivity {
TextView tvSum;
    PreferencesManager preferencesManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korzina);

        tvSum=findViewById(R.id.tvSum);

      //  tvSum.setText(preferencesManager.getSum());
    }
}