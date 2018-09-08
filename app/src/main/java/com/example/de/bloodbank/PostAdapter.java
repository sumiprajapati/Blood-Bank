package com.example.de.bloodbank;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostAdapter extends BaseAdapter {
    List<PostModule> mypost=new ArrayList<>();
    Context c;
    public PostAdapter(FragmentActivity activity, List<PostModule> publicpost) {
        c=activity;
        mypost=publicpost;
        Collections.reverse(mypost);
    }

    @Override
    public int getCount() {
        return mypost.size();
    }

    @Override
    public Object getItem(int position) {
        return mypost.get(getCount()-position-1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(c).inflate(R.layout.postlayout,null);
        TextView name=convertView.findViewById(R.id.name);
        TextView post=convertView.findViewById(R.id.post);
        name.setText(mypost.get(position).getName());
        post.setText(mypost.get(position).getPost());
        return convertView;
    }
}
