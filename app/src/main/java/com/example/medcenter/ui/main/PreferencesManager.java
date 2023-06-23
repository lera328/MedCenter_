package com.example.medcenter.ui.main;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String ONBOARD_KEY = "onboard";
    private static final String REGISTRATION_KEY = "onboard";
    private static final String PASSWORD_KEY = "onboard";
    private static final String SUM_KEY = "sum";
    private static final String PACIENT_KEY="pacient";
    private static final String ISPACIENT_KEY="ispacient";
    private static final String FOTO="foto";
    private static final String PASSWORD="pass";

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
    public int isSetPassword() {
        return preferences.getInt(PASSWORD, 0);
    }

    public void setOnboardShown(boolean isShown) {
        preferences.edit().putBoolean(ONBOARD_KEY, isShown).apply();
    }
    public void setPassword(int pass) {
        preferences.edit().putInt(PASSWORD, pass).apply();
    }

    public void setRegistrationShown(boolean isShown,int pass) {
        preferences.edit().putBoolean(REGISTRATION_KEY, isShown).apply();
        preferences.edit().putInt(PASSWORD_KEY, pass).apply();
    }


}
