package com.example.medcenter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnalisResult {
    @SerializedName("count")
    private String count;    @SerializedName("next")
    private String next;    @SerializedName("previous")
    private String previous;    @SerializedName("results")

    private List<Analis> analyses;
    public List<Analis> getAnalyses() {
        return analyses;    }
}
