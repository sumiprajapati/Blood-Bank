package com.example.de.bloodbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditDataFragment extends Fragment {
    String choosedaddress=null;
    String choosdebloodgroup=null;
    DatabaseReference db2;
    String[] bloodgroup={"-Select Blood Group-","A+ve","B+ve","AB+ve","O+ve","A-ve","B-ve","AB-ve","O-ve"};
    String[] address={"-Select Address-","Achham","Arghakhanchi","Baglung","Baitadi","Bajhang","Bajura","Banke"};
    Spinner bloodspinner,addressspinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.addlayout,null);
        bloodspinner=v.findViewById(R.id.bloodspinner);
        addressspinner=v.findViewById(R.id.addressspinner);
        final EditText ename=v.findViewById(R.id.name);
        final EditText eaddress=v.findViewById(R.id.address);
//        final EditText eage=v.findViewById(R.id.age);
        final EditText ephone=v.findViewById(R.id.phone);
        final EditText ebloodgroup=v.findViewById(R.id.bloodgroup);
        Button save=v.findViewById(R.id.save);
        Button cancel=v.findViewById(R.id.cancel);

        ArrayAdapter<String> bloodadapter=new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,
                bloodgroup);
        bloodspinner.setAdapter(bloodadapter);
        bloodspinner.setOnItemSelectedListener(new BloodSpinner());


        ArrayAdapter<String> addressadapter=new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,
                address);
        addressspinner.setAdapter(addressadapter);
        addressspinner.setOnItemSelectedListener(new AddressSpinner());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Myprofile1.fm.beginTransaction().replace(R.id.framelayout,new MyProfile()).commit();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataModule m=new DataModule();
                String myid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                db2=FirebaseDatabase.getInstance().getReference().child("user").child(myid);
                String myname=ename.getText().toString();
                String myaddress=choosedaddress;
//                String myage=eage.getText().toString();
                String myphone=ephone.getText().toString();
                String mybloodgroup=choosdebloodgroup;
                String myblood_address=myaddress+"_"+mybloodgroup;
                m.setName(myname);
                m.setAddress(myaddress);
                m.setPhone(myphone);
                m.setBloodgroup_address(myblood_address);
                m.setBloodgroup(mybloodgroup);
                m.setId(myid);
                m.setDoner("YES");
                db2.setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                        Myprofile1.fm.beginTransaction().replace(R.id.framelayout,new MyProfile()).commit();

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
    private class BloodSpinner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            choosdebloodgroup=bloodgroup[i];
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class AddressSpinner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            choosedaddress=address[i];

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    }

