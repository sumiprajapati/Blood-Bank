package com.example.de.bloodbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

public class SwipableFragment extends Fragment {
    PagerSlidingTabStrip pagertab;
    ViewPager vp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.swipabletab,null);
       pagertab=v.findViewById(R.id.pagertab);
       vp=v.findViewById(R.id.viewpager);
        FragmentManager fm=getChildFragmentManager();
        vp.setAdapter(new MyAdapter(getActivity(),fm));
        pagertab.setViewPager(vp);
       return v;

    }
}
