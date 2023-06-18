package com.example.medcenter;

import android.graphics.Bitmap;

public class CardPacient {
    public String name;
    public String firstName;
    public String secondName;
    public String age;
    public String pol;
    Bitmap foto;


    public CardPacient(String name,String firstName, String secondName, String age, String pol){
        this.name=name;
        this.firstName=firstName;
        this.secondName=secondName;
        this.age=age;
        this.pol=pol;
    }
    public CardPacient(String name,String firstName, String secondName, String age, String pol,Bitmap foto){
        this.name=name;
        this.firstName=firstName;
        this.secondName=secondName;
        this.age=age;
        this.pol=pol;
        this.foto=foto;
    }
}
