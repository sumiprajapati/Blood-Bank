package com.example.de.bloodbank;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends Fragment {
    FragmentTransaction ft;
        TextView profile, posts, lastDonation,logout;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View v= inflater.inflate(R.layout.dash,null);
            profile=v.findViewById(R.id.profilee);
            posts=v.findViewById(R.id.postt);
            lastDonation=v.findViewById(R.id.donationn);
            logout=v.findViewById(R.id.logout);



            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getActivity(),Myprofile1.class);
                    startActivity(i);

                }
            });

            posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getActivity(),MyPost.class);
                    startActivity(i);

                }
            });

            lastDonation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getActivity(),MyLastDonation.class);
                    startActivity(i);

                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   //for saving login session
                    SharedPreferences sp1 = getActivity().getSharedPreferences("yourfile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp1.edit();//shared preferance lai edit garna lai editor banako
                    editor.putBoolean("state", false);//login state lai true gareko
                    editor.commit();
                    Intent in=new Intent(getContext(),MainActivity.class);
                    startActivity(in);
                }
            });
            return v;
        }



}
