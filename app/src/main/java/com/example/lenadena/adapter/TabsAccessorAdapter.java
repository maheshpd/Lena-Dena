package com.example.lenadena.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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

                return new DailyExpanseFragment();
            case 1:
                return new LenaFragment();
            case 2:
                return new DenaFragement();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Daily Expanse";

            case 1:
                return "Lena";
            case 2:
                return "Dena";
            default:
                return null;
        }
    }
}
