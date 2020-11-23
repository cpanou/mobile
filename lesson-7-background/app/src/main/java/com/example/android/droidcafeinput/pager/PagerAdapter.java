package com.example.android.droidcafeinput.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.droidcafeinput.background.BackgroundFragment;
import com.example.android.droidcafeinput.http.HttpFragment;
import com.example.android.droidcafeinput.recyclerview.CookingFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private final int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new HttpFragment();
            case 1: return new BackgroundFragment();
            case 2: return new CookingFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
