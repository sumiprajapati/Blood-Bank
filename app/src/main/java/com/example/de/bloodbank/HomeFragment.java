package com.example.de.bloodbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    TextView nametextview;
    ListView recentpost;
    DatabaseReference db,db1;
    Query db2;
    String name;
    Button post;
    List<PostModule> publicpost=new ArrayList<>();
    EditText status;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home,null);
//        nametextview=v.findViewById(R.id.name);
        post=v.findViewById(R.id.post);
        status=v.findViewById(R.id.status);
        recentpost=v.findViewById(R.id.list);
        db1=FirebaseDatabase.getInstance().getReference().child("Post");
        db2=FirebaseDatabase.getInstance().getReference().child("Post");
        onResume();
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mypost=status.getText().toString();
                PostModule pm=new PostModule();
                pm.setName(name);
                pm.setPost(mypost);
                status.setText(null);
                db1.push().setValue(pm).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "You have added post", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        db= FirebaseDatabase.getInstance().getReference().child("user").child(id).child("name");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 name=dataSnapshot.getValue().toString();
//                nametextview.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                publicpost.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    PostModule post=new PostModule();
                    post.setName(ds.child("name").getValue().toString());
                    post.setPost(ds.child("post").getValue().toString());
                    publicpost.add(post);
                }

                recentpost.setAdapter(new PostAdapter(getActivity(),publicpost));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
