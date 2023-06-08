package com.example.medcenter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AdapterForAnalis extends FragmentPagerAdapter {
    public AdapterForAnalis(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return new Fragment_anPopular();
            case 1:
                return new Fragment_aCovid();

                case 2:
                return new Fragment_anComplex();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
