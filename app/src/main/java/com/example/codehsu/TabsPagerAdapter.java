package info.androidhive.tabsswipe.adapter;

import info.androidhive.tabsswipe.SignUp;
import info.androidhive.tabsswipe.SignIn;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // sign in
                return new info.androidhive.tabsswipe.SignIn();
            case 1:
                //sign up
                return new info.androidhive.tabsswipe.SignUp();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}