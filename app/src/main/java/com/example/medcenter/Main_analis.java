package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.medcenter.ui.main.PreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Main_analis extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, cardAnalisAdapterNew.OnCardClickListener, TextWatcher {
    RecyclerView recyclerView;
    ViewPager viewPager;
    Button bt1,bt2,bt3,myButton;
    int price;
    PreferencesManager preferencesManager;
    DbHelperK dbHelperK;
    TextView tvFind;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_analis);

        preferencesManager=new PreferencesManager(this);

        dbHelperK=new DbHelperK(this);

        tvFind=findViewById(R.id.find);
        tvFind.addTextChangedListener(this);


            myButton=findViewById(R.id.btVkorziny);
            myButton.setText("В корзину\t\t\t\t\t\t\t\t\t\t\t\t"+dbHelperK.Sum()+" ₽");
            if (dbHelperK.Sum()>0) myButton.setVisibility(View.VISIBLE);



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.main_analis);
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
                if (item.getItemId() == R.id.main_support) {
                    startActivity(new Intent(getApplicationContext(), MainSupport.class));
                    overridePendingTransition(0, 0);return true;
                }
                return true;
            }
        });
////////////////////////////////////////////////////////////





        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_analis.this, ActivityKorzina.class);
                startActivity(intent);
            }
        });
        /////////////////////////////nт МБ ОШИБКАААА








        List<cardBanerModel> cardList = new ArrayList<>();
        cardList.add(new cardBanerModel("Подготовка к вакцинации", "Комплексное обследование\n" +
                "перед вакцинацией", "4000 ₽ " , getResources().getDrawable(R.drawable.img_baner2)));
        cardList.add(new cardBanerModel("Чек-ап для мужчин", "9 исследований", "8000 ₽ ",getResources().getDrawable(R.drawable.img_baner1)));
        CardBanerAdapter adapter = new CardBanerAdapter(cardList,getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


        viewPager = findViewById(R.id.vp);

        AdapterForAnalis adapter1 = new AdapterForAnalis(getSupportFragmentManager());

        viewPager.setAdapter(adapter1);

        bt1=findViewById(R.id.btPopular);
        bt2=findViewById(R.id.btCovid);
        bt3=findViewById(R.id.btComplex);
        bt1.setEnabled(false);
        bt1.setTextColor(getColor(R.color.white));


    }



    public void onClick(View view) {
        switch (viewPager.getCurrentItem()){
            case 0: bt1.setEnabled(true); bt1.setTextColor(getColor(R.color.grey_text));break;
            case 1:bt2.setEnabled(true);bt2.setTextColor(getColor(R.color.grey_text));break;
            case 2: bt3.setEnabled(true);bt3.setTextColor(getColor(R.color.grey_text));break;
        }
        Button bt=(Button) view;
        if(bt.getId()==R.id.btPopular) {viewPager.setCurrentItem(0,true);bt.setEnabled(false); bt.setTextColor(getColor(R.color.white));}
        if(bt.getId()==R.id.btCovid) {viewPager.setCurrentItem(1,true);bt.setEnabled(false);bt.setTextColor(getColor(R.color.white));}
        if(bt.getId()==R.id.btComplex) {viewPager.setCurrentItem(2,true);bt.setEnabled(false);bt.setTextColor(getColor(R.color.white));}

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

Boolean buttonIsCreated=false;




    @Override
    public void onCardClickNew(int position, int cost, String text, String name) {
        if(!buttonIsCreated){
            buttonIsCreated=true;
        }

        int sum=0;
        if(text=="Убрать"){
            sum=dbHelperK.Sum();

        }
        else{
            sum=dbHelperK.Sum();
            //dbHelperK.deleteRow(name);
        }
        if(text=="В корзину"){
            Intent intent=new Intent(Main_analis.this,ActivityKorzina.class);
            startActivity(intent);
        }
        myButton.setText("В корзину\t\t\t\t\t\t\t\t\t\t\t\t"+dbHelperK.Sum()+" ₽");
        if (dbHelperK.Sum()>0) myButton.setVisibility(View.VISIBLE);
        else myButton.setVisibility(View.GONE);




    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String text=s.toString();
        //Fragment_anPopular.filterElements(text);
    }




    @Override
    public void afterTextChanged(Editable s) {

    }
}