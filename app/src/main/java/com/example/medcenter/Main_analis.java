package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.medcenter.ui.main.PreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Main_analis extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener , CardAnalisAdapter.OnCardClickListener {
    RecyclerView recyclerView;
    ViewPager viewPager;
    Button bt1,bt2,bt3,myButton;
    int price;
    PreferencesManager preferencesManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_analis);

        preferencesManager=new PreferencesManager(this);




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








        List<cardBanerModel> cardList = new ArrayList<>();
        cardList.add(new cardBanerModel("Подготовка к вакцинации", "Комплексное обследование\n" +
                "перед вакцинацией", "4000 ₽ " , getResources().getDrawable(R.drawable.img_baner2)));
        cardList.add(new cardBanerModel("Чек-ап для мужчин", "9 исследований", "8000 ₽ ",getResources().getDrawable(R.drawable.img_baner1)));
        CardBanerAdapter adapter = new CardBanerAdapter(cardList,getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView1);
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
    public void onCardClick(int position,int cost,String text) {


        if(!buttonIsCreated){
            myButton= new Button(this);
            LinearLayout layout=findViewById(R.id.layout_k);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20,24,20,24);
            myButton.setLayoutParams(params);
            myButton.setBackground(getDrawable(R.drawable.button_blue));
            myButton.setTextColor(getResources().getColor(R.color.white));
            myButton.setAllCaps(false);
            myButton.setGravity(View.TEXT_ALIGNMENT_CENTER);
            layout.addView(myButton);
            price=preferencesManager.getSum();
            buttonIsCreated=true;
        }

        int sum=0;
        if(text=="Убрать"){
            sum=cost;
        }
        else sum=-cost;

        price+=sum;
        preferencesManager.setSum(sum);
        myButton.setText("В корзину\t\t\t"+price);
        }
}