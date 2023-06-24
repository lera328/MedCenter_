package com.example.medcenter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AdapterF extends FragmentPagerAdapter//для фрагментов анализы, уведомления..{
{
    public AdapterF(@NonNull FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment_analis();
            case 1:
                return new Fragment_notification();
            case 2:
                return new Fragment_monitoring();
            default:
                return new Fragment_analis();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}


