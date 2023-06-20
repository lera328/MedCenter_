package com.example.medcenter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class Analis implements Serializable {

    private int id;
    private String name;
    private String description;
    private String price;
    private String category;
    private String time_result;
    private String preparation;
    private String bio;

    public Analis(int id,String name,String description, String price, String category, String time_result, String preparation, String bio){
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.category=category;
        this.time_result=time_result;
        this.preparation=preparation;
        this.bio=bio;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime_result() {
        return time_result;
    }

    public void setTime_result(String time_result) {
        this.time_result = time_result;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvTime;
        public TextView tvPrice;
        public Button buttonGet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            buttonGet = itemView.findViewById(R.id.btPlus);
        }
    }
}
