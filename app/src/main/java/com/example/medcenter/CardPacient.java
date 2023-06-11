package com.example.medcenter;

import java.util.Date;

public class CardPacient {
    public String name;
    public String firstName;
    public String secondName;
    public Date age;
    public String pol;



    public CardPacient(String name,String firstName, String secondName, Date age, String pol){
        this.name=name;
        this.firstName=firstName;
        this.secondName=secondName;
        this.age=age;
        this.pol=pol;
    }
}
