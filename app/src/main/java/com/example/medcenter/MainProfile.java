package com.example.medcenter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medcenter.ui.main.PreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class MainProfile extends AppCompatActivity {
PreferencesManager manager;
    Spinner spFloors;
    Button  btCreate;
    EditText etName,etSname,etFname,etAge;
    Calendar calendar;
    Bitmap selectedImage;
    String age;
    ImageView imgFoto;
    DbHelperK dbHelperK;
    private final int Pick_image = 1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        manager=new PreferencesManager(this);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.main_profile);

        dbHelperK=new DbHelperK(this);
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
        imgFoto=findViewById(R.id.imgFoto);
        imgFoto.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.img_foto));


       //if(manager.isPacient()==true){
       //    CardPacient pacient=manager.getPacient();
       //   // imgFoto.setImageBitmap(manager.getFoto());
       //    etSname.setText(pacient.getSecondName());
       //    etFname.setText(pacient.getFirstName());
       //    etName.setText(pacient.getName());
       //    etAge.setText(pacient.getAge());
       //    if(pacient.getPol()=="Мужской") spFloors.setSelection(0);
       //    else spFloors.setSelection(1);
       //}
        CardPacient pacient=dbHelperK.getPacient();
        if(pacient!=null){
            etSname.setText(pacient.getSecondName());
            etFname.setText(pacient.getFirstName());
            etName.setText(pacient.getName());
            etAge.setText(pacient.getAge());
            if(pacient.getPol()=="Мужской") spFloors.setSelection(0);
            else spFloors.setSelection(1);
            if(pacient.getFoto()!=null){imgFoto.setImageBitmap(Utilities.getImage(pacient.getFoto()));}
        }


        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                //Тип получаемых объектов - image:
                photoPickerIntent.setType("image/*");
                //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
                startActivityForResult(photoPickerIntent, Pick_image);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case Pick_image:
                if(resultCode == RESULT_OK) {
                    try {

                        //Получаем URI изображения, преобразуем его в Bitmap
                        //объект и отображаем в элементе ImageView нашего интерфейса:
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        selectedImage = BitmapFactory.decodeStream(imageStream);
                        imgFoto.setImageBitmap(selectedImage);
                        imgFoto.setRotation(90);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public void onClick(View view) {
        if(view.getId()==R.id.buttonCreate){
            if( Valid(etName.getText().toString(),
                etFname.getText().toString(),etSname.getText().toString(),age,spFloors.getSelectedItem().toString())) {
            CardPacient pacient = new CardPacient(etName.getText().toString(),
                    etFname.getText().toString(), etSname.getText().toString(), age,
                    spFloors.getSelectedItem().toString(), Utilities.getBytes(selectedImage));

            dbHelperK.deleteAllPerson();
            dbHelperK.addNewPerson(etName.getText().toString(),
                    etFname.getText().toString(), etSname.getText().toString(), age,
                    spFloors.getSelectedItem().toString(), Utilities.getBytes(selectedImage));


            Toast.makeText(this, "Карта создана", Toast.LENGTH_LONG).show();
        }
        }

       if(view.getId()==R.id.etAge) {
           calendar=Calendar.getInstance();
          try{ int year = calendar.get(Calendar.YEAR);
           int month = calendar.get(Calendar.MONTH);
           int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
           DatePickerDialog datePickerDialog = new DatePickerDialog(MainProfile.this, new DatePickerDialog.OnDateSetListener() {
               @Override
               public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   etAge.setText(dayOfMonth + "." + (month + 1) + "." + year);

                   calendar.set(year, month, dayOfMonth);
                   age = dayOfMonth + "." + (month + 1) + "." + year;
               }
           }, year, month, dayOfMonth);
           datePickerDialog.show();}
          catch (Exception exception){
              Toast.makeText(this,exception.toString(),Toast.LENGTH_LONG).show();
              Log.e("aaaa",exception.toString());
          }
       }
    }
    public boolean Valid(String name, String fName, String sName, String age, String pol){
        if (name!="" && fName!="" && sName!="" && age!=null && pol!=""){
            return true;
        }
        return false;
    }
}