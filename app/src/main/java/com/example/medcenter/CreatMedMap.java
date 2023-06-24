package com.example.medcenter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CreatMedMap extends AppCompatActivity implements View.OnClickListener {
Spinner spFloors;
 Button btNext, btCreate;
 EditText etName,etSname,etFname,etAge;
 Calendar calendar;
 String age;
 DbHelperK dbHelperK;
 int f=0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_med_map);
        dbHelperK=new DbHelperK(this);
        spFloors=findViewById(R.id.spinner);
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.floors,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Вызываем адаптер
        spFloors.setAdapter(adapter);

        btCreate=findViewById(R.id.buttonCreate);

        btNext=findViewById(R.id.button);


        etName=findViewById(R.id.etName);
        etFname=findViewById(R.id.etFName);
        etSname=findViewById(R.id.etSName);
        etAge=findViewById(R.id.etAge);
        etAge.setOnClickListener(this::onClick);
        calendar = Calendar.getInstance();

        Intent intent=getIntent();
        f=intent.getIntExtra("activity",0);
        if(f==0 && dbHelperK.getPacient()!=null){
            Intent intent1=new Intent(CreatMedMap.this,Main_analis.class);
            startActivity(intent1);
        }


    }

    public void onClick(View view) {
        if(view.getId()==R.id.button){


        Intent intent1=new Intent(CreatMedMap.this,Main_analis.class);
        startActivity(intent1);}
        if(view.getId()==R.id.buttonCreate){if( Valid(etName.getText().toString(),
                etFname.getText().toString(),etSname.getText().toString(),
                age,spFloors.getSelectedItem().toString())){
            CardPacient pacient=new CardPacient(etName.getText().toString(),
                            etFname.getText().toString(),etSname.getText().toString(),age,spFloors.getSelectedItem().toString());
            dbHelperK.addNewPerson(etName.getText().toString(),
                    etFname.getText().toString(),etSname.getText().toString(),age,spFloors.getSelectedItem().toString(),null);
            Toast.makeText(this,"Карта создана",Toast.LENGTH_LONG).show();
            if(f==1){
                Intent intent1=new Intent(CreatMedMap.this,ActivityOformlenieZ.class);
                intent1.putExtra("activity",1);
                startActivity(intent1);
            }
            else {
            Intent intent1=new Intent(CreatMedMap.this,Main_analis.class);
            startActivity(intent1);}
        }
        else Toast.makeText(this,"Неверные данные",Toast.LENGTH_LONG).show();
        }

        if(view.getId()==R.id.etAge) {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(CreatMedMap.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    etAge.setText(dayOfMonth + "." + (month + 1) + "." + year);

                    calendar.set(year, month, dayOfMonth);
                    age = dayOfMonth + "." + (month + 1) + "." + year;
                }
            }, year, month, dayOfMonth);
            datePickerDialog.show();
        }
    }


    public boolean Valid(String name, String fName, String sName, String age, String pol){
        if (name!="" && fName!="" && sName!="" && age!=null && pol!=""){
            return true;
        }
        return false;
    }
}