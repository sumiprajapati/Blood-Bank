package com.example.de.bloodbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends Fragment{
    DatabaseReference db1,db2;
    Button edit;
    TextView topname,name,address,age,phone,bloodgroup,doner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.myprofile,null);
        return  v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topname=view.findViewById(R.id.topname);
        name=view.findViewById(R.id.name);
        address=view.findViewById(R.id.address);
//        age=view.findViewById(R.id.age);
        phone=view.findViewById(R.id.phone);
        bloodgroup=view.findViewById(R.id.bloodgroup);
//        doner=view.findViewById(R.id.bedoner);
        edit=view.findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Myprofile1.fm.beginTransaction().replace(R.id.framelayout,new EditDataFragment()).commit();
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        final String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        db1= FirebaseDatabase.getInstance().getReference().child("user").child(id);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                topname.setText(dataSnapshot.child("name").getValue().toString());
                name.setText(dataSnapshot.child("name").getValue().toString());
                address.setText(dataSnapshot.child("address").getValue().toString());
//                age.setText(dataSnapshot.child("age").getValue().toString());
                phone.setText(dataSnapshot.child("phone").getValue().toString());
                bloodgroup.setText(dataSnapshot.child("bloodgroup").getValue().toString());
//                doner.setText(dataSnapshot.child("doner").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
