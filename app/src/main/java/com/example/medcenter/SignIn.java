package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity implements TextWatcher, View.OnClickListener {
Button bt_next;
EditText etEmail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        bt_next=findViewById(R.id.bt_next);
        etEmail=findViewById(R.id.etEmail);

        etEmail.addTextChangedListener(this);
        bt_next.setEnabled(false);
        bt_next.setOnClickListener(this);

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches())
            bt_next.setEnabled(true);
        else bt_next.setEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(SignIn.this,CodeEmail.class);
        startActivity(intent);
    }
}