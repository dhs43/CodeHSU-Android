package com.example.codehsu.LoginTabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        FragmentTab fragmentTab = new FragmentTab();
        position = position + 1;
        Bundle bundle = new Bundle();
        bundle.putString("message", "Fragment :"+position);
        fragmentTab.setArguments(bundle);
        return fragmentTab;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        position = position+1;
        return "Fragment "+position;
    }
}
