
package com.example.medcenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.santalu.maskara.widget.MaskEditText;

import java.util.ArrayList;
import java.util.List;

public class ActivityOformlenieZ extends AppCompatActivity implements AdapterForAddPerson.OnItemClickListener{
    Button btGetZakaz, btPlusPerson;
    EditText etDateTime, etComent, etAdress;
    MaskEditText etNumber;
    DbHelperK dbHelperK;
    Spinner spinnerPerson;
    ImageButton btMicro;
    TextView tvKolAnalis, tvSumAnalis;
    RecyclerView recyclerView_pacient_analis;
    List<String> personl;
    List<Integer> personl_pos;
    Adapter_person_analis adapter_person_analis;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oformlenie_z);
        TextView tvWho=findViewById(R.id.tvWho);
        String t = "<font color=#7E7E9A>Кто будет сдавать анализы?</font> <font color=#B71C1C>*</font>";
        tvWho.setText(Html.fromHtml(t));
        btGetZakaz=findViewById(R.id.btGetZakaz);
        btGetZakaz.setEnabled(false);
        etDateTime=findViewById(R.id.etDateTime);
        etAdress=findViewById(R.id.etAdress);
        etNumber=findViewById(R.id.etNumber);
        btPlusPerson=findViewById(R.id.btPlusPacient);
        recyclerView_pacient_analis=findViewById(R.id.receyler_pacient_analis);
        Intent intent=getIntent();

        if(intent.getIntExtra("activity",0)==1){
            btPlusPerson.performClick();
        }
        etDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(ActivityOformlenieZ.this,R.style.BottomSheetDialogTheme);
                View bottomSheetView= LayoutInflater.from(ActivityOformlenieZ.this)
                        .inflate(R.layout.bottom_sheet_adress,
                                (LinearLayout)findViewById(R.id.container));


                String[] dates = {"Сегодня, 24 июня", "25 июня", "26 июня", "27 июня", "28 июня"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(bottomSheetView.getContext(), android.R.layout.simple_spinner_item, dates);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityOformlenieZ.this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, catNames);

                Spinner spinner=bottomSheetView.findViewById(R.id.spinner3);
                spinner.setAdapter(adapter);
                RadioButton radioButton1 = bottomSheetView.findViewById(R.id.radioButton);
                RadioButton radioButton2 = bottomSheetView.findViewById(R.id.radioButton2);
                RadioButton radioButton3 = bottomSheetView.findViewById(R.id.radioButton3);
                RadioButton radioButton4 = bottomSheetView.findViewById(R.id.radioButton4);
                RadioGroup radioGroup = bottomSheetView.findViewById(R.id.radioGroup);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        // Получаем ссылку на выбранный RadioButton
                        RadioButton checkedRadioButton = bottomSheetView.findViewById(checkedId);
                        radioButton1.setEnabled(true);
                        radioButton2.setEnabled(true);
                        radioButton3.setEnabled(true);
                        radioButton4.setEnabled(true);
                        // Устанавливаем цвет фона выбранной кнопки
                        checkedRadioButton.setEnabled(false);
                    }
                });


                ImageButton btClose=bottomSheetView.findViewById(R.id.btClose);
                btClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        etDateTime.setHint("Выберите дату и время");
                    }
                });
                Button btsave=bottomSheetView.findViewById(R.id.btSave);
                btsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                        //Получаем выбранный элемент из Spinner
                        String selectedDate = (String) ((Spinner)bottomSheetView.findViewById(R.id.spinner3)).getSelectedItem();

                        // Получаем выбранную кнопку из RadioGroup
                        int checkedRadioButtonId = ((RadioGroup)bottomSheetView.findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
                        RadioButton checkedRadioButton = bottomSheetView.findViewById(checkedRadioButtonId);
                        String selectedRadioButtonText = checkedRadioButton.getText().toString();

                        // Устанавливаем текст в ваше текстовое поле

                        String text = selectedRadioButtonText + " " + selectedDate;
                        etDateTime.setText(text);
                        bottomSheetDialog.dismiss();}
                        catch (Exception e){
                            Toast.makeText(ActivityOformlenieZ.this,"не все поля заполнены",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        dbHelperK=new DbHelperK(this);
        List<CardPacient> pacientList=dbHelperK.getPersonList();
        List<String> spinnerPacientList = new ArrayList<>();
        for (CardPacient pacient : pacientList) {
            if (pacient.getPol().contains("Женский"))
                spinnerPacientList.add("\uD83D\uDC69 \t"+pacient.getSecondName() + " " + pacient.getName());
            else spinnerPacientList.add("\uD83D\uDC68 \t"+pacient.getSecondName() + " " + pacient.getName());
        }

        spinnerPerson = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerPacientList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPerson.setAdapter(adapter);

        personl=new ArrayList<>();
        personl_pos=new ArrayList<>();
        adapter_person_analis=new Adapter_person_analis(ActivityOformlenieZ.this,personl_pos);

        recyclerView_pacient_analis.setVisibility(View.VISIBLE);
        recyclerView_pacient_analis.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView_pacient_analis.setAdapter(adapter_person_analis);

        btPlusPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(ActivityOformlenieZ.this,R.style.BottomSheetDialogTheme);
                View bottomSheetView= LayoutInflater.from(ActivityOformlenieZ.this)
                        .inflate(R.layout.bottom_sheet_getperson,
                                (LinearLayout)findViewById(R.id.container));
                RecyclerView spisok=bottomSheetView.findViewById(R.id.recycler);

                AdapterForAddPerson adapter=new AdapterForAddPerson(ActivityOformlenieZ.this,spinnerPacientList
                        ,(AdapterForAddPerson.OnItemClickListener) bottomSheetView.getContext());
                spisok.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityOformlenieZ.this);
                spisok.setLayoutManager(layoutManager);

                Button btGetP=bottomSheetView.findViewById(R.id.btGetP);
                btGetP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),CreatMedMap.class);
                        intent.putExtra("activity",1);
                        startActivity(intent);
                    }
                });

                ImageButton btClose=bottomSheetView.findViewById(R.id.btClose);
                btClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                Button btGet=bottomSheetView.findViewById(R.id.buttonGet);
                btGet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ////////////\
                        //personl.add(adapter.getSelectedItem());
                        personl_pos.add(adapter.getSelectedItem());
                        adapter_person_analis.notifyDataSetChanged();
                        spinnerPerson.setVisibility(View.GONE);
                        bottomSheetDialog.dismiss();


                        ////////////
                    }
                });


                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }


    @Override
    public void onItemClick(View view, int position, int SelectedPosition) {
        TextView tv=view.findViewById(R.id.textview);

        if (SelectedPosition == position) {
            //holder.itemView.setSelected(true); //using selector drawable
            tv.setBackground(getDrawable(R.drawable.shape_layout_select));
        } else {
            //holder.itemView.setSelected(false);
            tv.setBackground(getDrawable(R.drawable.shape_layout));
        }

    }

}