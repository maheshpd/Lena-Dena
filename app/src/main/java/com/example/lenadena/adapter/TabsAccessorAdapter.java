package com.example.lenadena.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lenadena.fragment.ALLFragment;
import com.example.lenadena.fragment.DailyExpanseFragment;
import com.example.lenadena.fragment.DenaFragement;
import com.example.lenadena.fragment.LenaFragment;

public class TabsAccessorAdapter extends FragmentPagerAdapter {

    public TabsAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                ALLFragment allFragment = new ALLFragment();
                return allFragment;
            case 1:
                DenaFragement denaFragement = new DenaFragement();
                return denaFragement;

            case 2:
                LenaFragment lenaFragment = new LenaFragment();
                return lenaFragment;
            case 3:
                DailyExpanseFragment dailyExpanseFragment = new DailyExpanseFragment();
                return dailyExpanseFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ALL";
            case 1:
                return "Dena";
            case 2:
                return "Lena";
            case 3:
                return "Daily Expanse";
            default:
                return null;
        }
    }
}
