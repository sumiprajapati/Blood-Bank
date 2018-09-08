package com.example.de.bloodbank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class Myprofile1 extends AppCompatActivity {
    public static FragmentManager fm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile1);
        fm=getSupportFragmentManager();
        fm.beginTransaction().add(R.id.framelayout,new MyProfile()).commit();
    }
}
