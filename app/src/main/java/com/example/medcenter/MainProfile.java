package com.example.medcenter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medcenter.ui.main.PreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

public class MainProfile extends AppCompatActivity {
PreferencesManager manager;
    Spinner spFloors;
    Button  btCreate;
    EditText etName,etSname,etFname,etAge;
    Calendar calendar;
    Date age;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        manager=new PreferencesManager(this);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.main_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_analis) {
                    startActivity(new Intent(getApplicationContext(), Main_analis.class));
                    overridePendingTransition(0, 0);return true;
                }
                if (item.getItemId() == R.id.main_res) {
                    startActivity(new Intent(getApplicationContext(), MainResult.class));
                    overridePendingTransition(0, 0);return true;
                }
                if (item.getItemId() == R.id.main_support) {
                    startActivity(new Intent(getApplicationContext(), MainSupport.class));
                    overridePendingTransition(0, 0);return true;
                }
                return true;
            }
        });
        etAge=findViewById(R.id.etAge);
        etName=findViewById(R.id.etName);
        etFname=findViewById(R.id.etFName);
        etSname=findViewById(R.id.etSName);
        spFloors=findViewById(R.id.spinner);
        btCreate=findViewById(R.id.buttonCreate);
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.floors,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Вызываем адаптер
        spFloors.setAdapter(adapter);


        if(manager.isPacient()==true){
            CardPacient pacient=manager.getPacient();
            etSname.setText(pacient.secondName);
            etFname.setText(pacient.firstName);
            etName.setText(pacient.name);
            etAge.setText(pacient.age.toString());
            if(pacient.pol=="Мужской") spFloors.setSelection(0);
            else spFloors.setSelection(1);
        }
    }

    public void onClick(View view) {
        if(view.getId()==R.id.buttonCreate){
            if( Valid(etName.getText().toString(),
                etFname.getText().toString(),etSname.getText().toString(),age,spFloors.getSelectedItem().toString())) {
            CardPacient pacient = new CardPacient(etName.getText().toString(),
                    etFname.getText().toString(), etSname.getText().toString(), age, spFloors.getSelectedItem().toString());
            PreferencesManager manager = new PreferencesManager(this);
            manager.setPacient(pacient);
            Toast.makeText(this, "Карта создана", Toast.LENGTH_LONG).show();
        }
        }

        if(view.getId()==R.id.etAge) {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainProfile.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    etAge.setText(dayOfMonth + "." + (month + 1) + "." + year);

                    calendar.set(year, month, dayOfMonth);
                    age = calendar.getTime();
                }
            }, year, month, dayOfMonth);
            datePickerDialog.show();
        }
    }
    public boolean Valid(String name, String fName, String sName, Date age, String pol){
        if (name!="" && fName!="" && sName!="" && age!=null && pol!=""){
            return true;
        }
        return false;
    }
}