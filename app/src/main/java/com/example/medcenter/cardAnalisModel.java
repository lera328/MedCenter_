package com.example.medcenter;

import java.io.Serializable;

public class cardAnalisModel implements Serializable {
    private String title;
    String time;
    String cost;
    int price;


    public cardAnalisModel(String tit, int tim, int c) {
        String s="";
        if(tim%10==1)s=" день";
        if(tim%10>1 && tim%10<5) s=" дня";
        if(tim%10>4 ||(tim>4 && tim<21)) s=" дней";
        this.title= tit;
        this.time= String.valueOf(tim)+s;
        this.cost= String.valueOf(c)+"₽";
        this.price=c;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setCountryName(String time) {
        this.time = time;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String c) {
        this.cost = c;
    }

    @Override
    public String toString()  {

        return this.title+"\n"+ this.time+"\n"+this.cost;
    }
}
