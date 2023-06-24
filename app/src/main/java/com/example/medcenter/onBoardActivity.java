package com.example.medcenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.medcenter.ui.main.PreferencesManager;

public class onBoardActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    //активити с фрагментами
    Button btNext;
    private ViewPager viewPager;
    PreferencesManager preferencesManager;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        intent=new Intent(onBoardActivity.this, SignIn.class);
        preferencesManager = new PreferencesManager(this);
        if (preferencesManager.isOnboardShown()) {//Отвечает за просмотр
            startActivity(intent);                //приветственного экрана
        }
        if (preferencesManager.isRegistred()) {//Отвечает за произведенную регистрацию
            Intent intent1=new Intent(onBoardActivity.this,EdPassword.class);
            startActivity(intent1);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        AdapterF adapter = new AdapterF(getSupportFragmentManager());
        ///Создание адаптера с фрагментами
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        btNext=findViewById(R.id.button_next);
        btNext.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        intent=new Intent(onBoardActivity.this, SignIn.class);
        startActivity(intent);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(viewPager.getCurrentItem()==2) {btNext.setText("Завершить");
            preferencesManager.setOnboardShown(true);}
        else btNext.setText("Пропустить");
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}