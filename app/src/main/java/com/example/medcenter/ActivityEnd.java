package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityEnd extends AppCompatActivity {
TextView tv;
Button btToMine;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        tv=findViewById(R.id.textView31);
        String text="<font color=#939396>Не забудьте ознакомиться с   </font> <font color=#1A6FEE>\uD83D\uDCC4правилами подготовки к сдаче анализов</font>";
        tv.setText(Html.fromHtml(text));

        btToMine=findViewById(R.id.btToMine);
        btToMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelperK dbHelperK=new DbHelperK(ActivityEnd.this);
                dbHelperK.deleteAllRow();
                Intent intent=new Intent(ActivityEnd.this, Main_analis.class);
                startActivity(intent);
            }
        });
    }
}