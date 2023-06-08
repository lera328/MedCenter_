package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CreatMedMap extends AppCompatActivity {
Spinner spFloors;
 Button btNext, btCreate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_med_map);

        spFloors=findViewById(R.id.spinner);
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.floors,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Вызываем адаптер
        spFloors.setAdapter(adapter);

        btCreate=findViewById(R.id.buttonCreate);
        btNext=findViewById(R.id.button);
    }



    public void onClick(View view) {
        Intent intent1=new Intent(CreatMedMap.this,Main_analis.class);
        startActivity(intent1);
    }
}