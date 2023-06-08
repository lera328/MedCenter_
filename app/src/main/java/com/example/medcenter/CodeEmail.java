package com.example.medcenter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CodeEmail extends AppCompatActivity  {
EditText etC1,etC2,etC3,etC4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_email);
        etC1=findViewById(R.id.etCode1);
        etC2=findViewById(R.id.etCode2);
        etC3=findViewById(R.id.etCode3);
        etC4=findViewById(R.id.etCode4);
        etC1.requestFocus();
        etC1.setInputType(InputType.TYPE_CLASS_NUMBER);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        TextWatcher tw =new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etC1.getText().length()==1) etC2.requestFocus();
                if(etC2.getText().length()==1) etC3.requestFocus();
                if(etC3.getText().length()==1) etC4.requestFocus();
                if(etC1.getText().length()+etC2.getText().length()+etC3.getText().length()+etC4.getText().length()==4){
                    Intent intent=new Intent(CodeEmail.this,EdPassword.class);
                    startActivity(intent);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        etC1.addTextChangedListener(tw);
        etC2.addTextChangedListener(tw);
        etC3.addTextChangedListener(tw);
        etC4.addTextChangedListener(tw);
    }
}