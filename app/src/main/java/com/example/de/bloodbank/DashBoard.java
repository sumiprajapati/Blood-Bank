package com.example.de.bloodbank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

public class DashBoard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    FragmentTransaction ft;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashlayout);

        ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.framelayout,new SwipableFragment());
        ft.commit();


    }
}
