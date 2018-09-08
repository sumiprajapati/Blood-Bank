package com.example.de.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BloodAdapter extends BaseAdapter {
    Context c;
    List<SearchModule> data=new ArrayList<>();
    public BloodAdapter(FragmentActivity activity, List<SearchModule> mydata) {
        c=activity;
        data=mydata;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(c).inflate(R.layout.bloodlistlayout,null);
        TextView name=convertView.findViewById(R.id.name);
        TextView address=convertView.findViewById(R.id.address);
        TextView phone=convertView.findViewById(R.id.phone);
        final String myname=data.get(position).getName();
        final String myaddress=data.get(position).getAddress();
        final String mybloodgroup=data.get(position).getBloodgroup();
        final String myphone=data.get(position).getPhone();
        name.setText(myname);
        address.setText(myaddress);
        phone.setText(myphone);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(c,DonerView.class);
                Bundle b=new Bundle();
                b.putString("name",myname);
                b.putString("address",myaddress);
                b.putString("phone",myphone);
                b.putString("bloodgroup",mybloodgroup);
                i.putExtras(b);
                c.startActivity(i);
            }
        });
        return convertView;
    }
}
