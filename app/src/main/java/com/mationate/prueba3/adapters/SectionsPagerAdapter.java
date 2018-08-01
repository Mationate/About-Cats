package com.mationate.prueba3.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mationate.prueba3.views.tabs.CardFragment;
import com.mationate.prueba3.views.tabs.FavoriteFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0: return CardFragment.newInstance();
           case 1: return FavoriteFragment.newInstance();
           default: return CardFragment.newInstance();
       }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
