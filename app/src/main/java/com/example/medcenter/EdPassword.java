package com.example.medcenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medcenter.ui.main.PreferencesManager;

public class EdPassword extends AppCompatActivity implements View.OnClickListener {
ImageView point1, point2, point3,point4;
Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt0, bt_next;
ImageButton btDel;
String pass="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_password);
        PreferencesManager pm=new PreferencesManager(this);
        if (pm.isSetPassword()!=0){
            TextView tv=findViewById(R.id.textView7);
            tv.setText("Введите пароль");
        }
        point1=findViewById(R.id.point1);
        point2=findViewById(R.id.point2);
        point3=findViewById(R.id.point3);
        point4=findViewById(R.id.point4);

        bt0=findViewById(R.id.bt0);
        bt1=findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        bt3=findViewById(R.id.bt3);
        bt4=findViewById(R.id.bt4);
        bt5=findViewById(R.id.bt5);
        bt6=findViewById(R.id.bt6);
        bt7=findViewById(R.id.bt7);
        bt8=findViewById(R.id.bt8);
        bt9=findViewById(R.id.bt9);
        btDel=findViewById(R.id.btDel);

        point1.setEnabled(false);
        point2.setEnabled(false);
        point3.setEnabled(false);
        point4.setEnabled(false);
        btDel.setEnabled(false);

        bt_next=findViewById(R.id.button);
        bt_next.setOnClickListener(this::onClick);

    }

    public void onClick(View view) {
        if(view.getId()==R.id.button){
            Intent intent=new Intent(this,CreatMedMap.class);
            startActivity(intent);}
        if(view.getId()!=R.id.btDel){
        Button b=(Button) view;
        pass+=b.getText().toString();
        switch (pass.length()){
            case 1: point1.setEnabled(true);break;
            case 2: point2.setEnabled(true);break;
            case 3: point3.setEnabled(true);break;
            case 4: point4.setEnabled(true);setEnable(false);break;
        }

        btDel.setEnabled(true);
        }
        else {
            pass=pass.substring(0,pass.length()-1);
            switch (pass.length()){
                case 0: point1.setEnabled(false); btDel.setEnabled(false); break;
                case 1: point2.setEnabled(false);break;
                case 2: point3.setEnabled(false);break;
                case 3: point4.setEnabled(false);setEnable(true);break;

            }
        }
        if(pass.length()==4) {

            PreferencesManager preferencesManager=new PreferencesManager(this);
            if(preferencesManager.isSetPassword()==0){
            preferencesManager.setPassword(Integer.parseInt(String.valueOf(pass)));
            Toast.makeText(getApplicationContext(), "пароль сохранен!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CreatMedMap.class);
            startActivity(intent);}
            else {
                if(preferencesManager.isSetPassword()==Integer.parseInt(String.valueOf(pass)))
                {Intent intent = new Intent(this, CreatMedMap.class);
                startActivity(intent);
                }
                else Toast.makeText(this,"Неверный пароль!",Toast.LENGTH_LONG);
            }
        }


    }

    public void setEnable(boolean b){
        bt0.setEnabled(b);
        bt1.setEnabled(b);
        bt2.setEnabled(b);
        bt3.setEnabled(b);
        bt4.setEnabled(b);
        bt5.setEnabled(b);
        bt6.setEnabled(b);
        bt7.setEnabled(b);
        bt8.setEnabled(b);
        bt9.setEnabled(b);
    }
}