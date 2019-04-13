package com.example.codehsu.Login_Register;

import com.example.codehsu.Login_Register.SignIn;
import com.example.codehsu.Login_Register.SignUp;

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
                return new SignIn();
            case 1:
                //sign up
                return new SignUp();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}