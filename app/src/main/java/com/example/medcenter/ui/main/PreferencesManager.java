package com.example.medcenter.ui.main;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.medcenter.CardPacient;
import com.google.gson.Gson;

public class PreferencesManager {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String ONBOARD_KEY = "onboard";
    private static final String REGISTRATION_KEY = "onboard";
    private static final String PASSWORD_KEY = "onboard";
    private static final String SUM_KEY = "sum";
    private static final String PACIENT_KEY="pacient";
    private static final String ISPACIENT_KEY="ispacient";

    private SharedPreferences preferences;

    public PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean isOnboardShown() {
        return preferences.getBoolean(ONBOARD_KEY, false);
    }
    public boolean isRegistred() {
        return preferences.getBoolean(REGISTRATION_KEY, false);
    }

    public void setOnboardShown(boolean isShown) {
        preferences.edit().putBoolean(ONBOARD_KEY, isShown).apply();
    }

    public void setRegistrationShown(boolean isShown,int pass) {
        preferences.edit().putBoolean(REGISTRATION_KEY, isShown).apply();
        preferences.edit().putInt(PASSWORD_KEY, pass).apply();
    }
    public void setSum(int s){
        int sum= getSum()+s;
        preferences.edit().putInt(SUM_KEY,sum).apply();
    }



    public int getSum() {
        return preferences.getInt(SUM_KEY,0);
    }
     public void setPacient(CardPacient p){
         Gson gson = new Gson();
         String json = gson.toJson(p);
         preferences.edit().putString(PACIENT_KEY, json).apply();
         preferences.edit().putBoolean(ISPACIENT_KEY,true).apply();
     }
     public CardPacient getPacient(){
         Gson gson = new Gson();
         String json = preferences.getString(PACIENT_KEY, "");
         CardPacient pacient = gson.fromJson(json, CardPacient.class);
         return pacient;
     }

     public boolean isPacient(){

        Boolean r=preferences.getBoolean(ISPACIENT_KEY,false);
        return r;
     }
}
