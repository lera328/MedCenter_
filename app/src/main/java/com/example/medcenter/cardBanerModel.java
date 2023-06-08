package com.example.medcenter;

import android.graphics.drawable.Drawable;

public class cardBanerModel {
    private String title1;
    String title2;
    String cost;
    Drawable image;


    public cardBanerModel(String t1, String t2, String c,Drawable i) {
        this.title1= t1;
        this.title2= t2;
        this.cost= c;
        this.image=i;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title) {
        this.title1 = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable i) {
        this.image = i;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String c) {
        this.cost = c;
    }

    @Override
    public String toString()  {

        return this.title1+"\n"+ this.title2+"\n"+this.cost;
    }
}
