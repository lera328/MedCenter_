package com.example.medcenter;

import static com.example.medcenter.CardAdapterKorzina.OnCardClickListener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityKorzina extends AppCompatActivity implements OnCardClickListener, View.OnClickListener {
TextView tvSum;
DbHelperK dbHelperK;
ImageButton btBack, btDelAll;
Button btGoToOformlenie;
    CardAdapterKorzina adapterKorzina;

    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korzina);

        tvSum=findViewById(R.id.tvSum);
        dbHelperK=new DbHelperK(this);
        List<ObjectForKorzina> korzinaList = dbHelperK.getKorzina();
        adapterKorzina=new CardAdapterKorzina(korzinaList,this,(OnCardClickListener) this);
        recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapterKorzina);
        int sum=dbHelperK.Sum();
        tvSum.setText(sum+"");

        btBack=findViewById(R.id.imageButton);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityKorzina.this,Main_analis.class);
                startActivity(intent);
            }
        });
        btDelAll=findViewById(R.id.btDelAll);
        btDelAll.setOnClickListener(this);

        btGoToOformlenie=findViewById(R.id.btGoToOfofrmlenie);
        btGoToOformlenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityKorzina.this,
                        ActivityOformlenieZ.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCardClick(String text, boolean f, int price) {

        if(f){
            //dbHelperK.addNewInsult(text,price);
        }
        //else dbHelperK.deleteRow(text);

        int sum=dbHelperK.Sum();
        tvSum.setText(sum+"" );

        if (!f){
        List<ObjectForKorzina>korzinaList=dbHelperK.getKorzina();
        adapterKorzina=new CardAdapterKorzina(korzinaList,this,(OnCardClickListener) this);
        recyclerView.setAdapter(adapterKorzina);}

    }

    @Override
    public void onClick(View v) {
        dbHelperK.deleteAllRow();
        List<ObjectForKorzina>korzinaList=new ArrayList<>();
        adapterKorzina=new CardAdapterKorzina(korzinaList,this,(CardAdapterKorzina.OnCardClickListener) this);
        recyclerView.setAdapter(adapterKorzina);
        int sum=dbHelperK.Sum();
        tvSum.setText(sum+"" );
    }
}