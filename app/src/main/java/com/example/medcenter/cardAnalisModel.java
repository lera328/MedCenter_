package com.example.medcenter;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class cardAnalisModel implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String title;
    @SerializedName("description")
    String Description;
    @SerializedName("price")
    String cost;
    @SerializedName("category")
    private String category;
    @SerializedName("time_result")
    String time;

    @SerializedName("preparation")
    private String preparation;

    @SerializedName("bio")
    private String bio;


    public cardAnalisModel(String tit, String tim, String c) {

        this.title= tit;
        this.time= tim;
        this.cost= c;
    }

    public cardAnalisModel(String tit, int tim, int c) {
        String s="";
        if(tim%10==1)s=" день";
        if(tim%10>1 && tim%10<5) s=" дня";
        if(tim%10>4 ||(tim>4 && tim<21)) s=" дней";
        this.title= tit;
        this.time= String.valueOf(tim)+s;
        this.cost= String.valueOf(c);
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setId(int id) {
        this.id = id;
    }
}
