package com.example.de.bloodbank;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyAdapter extends FragmentStatePagerAdapter {
    public MyAdapter(FragmentActivity activity, FragmentManager fm) {
    super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment();
        }
        if (position == 1) {
            return new SearchBloodFragment();
        }
        if (position == 2) {
            return new Profile();
        } else {

            return null;
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Home";
        }
        if (position == 1) {
            return "Blood";
        }
        if (position == 2) {
            return "Profile";
        } else {

            return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
