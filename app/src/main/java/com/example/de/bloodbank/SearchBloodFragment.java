package com.example.de.bloodbank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchBloodFragment extends Fragment{
    String choosedaddress=null;
    String choosdebloodgroup=null;
    DatabaseReference db;
    Query db1;
    String[] bloodgroup={"All","A+ve","B+ve","AB+ve","O+ve","A-ve","B-ve","AB-ve","O-ve"};
    String[] address={"All","Achham","Arghakhanchi","Baglung","Baitadi","Bajhang","Bajura","Banke"};
    Spinner bloodspinner,addressspinner;
    List<SearchModule> mydata=new ArrayList<>();
    ListView lv;
    Button apply;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.search,null);
        lv=v.findViewById(R.id.bloodlist);
        bloodspinner=v.findViewById(R.id.bloodspinner);
        addressspinner=v.findViewById(R.id.addressspinner);
        db1= FirebaseDatabase.getInstance().getReference().child("user");
        apply=v.findViewById(R.id.apply);


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
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(choosedaddress.equalsIgnoreCase("all")&&choosdebloodgroup.equalsIgnoreCase("all")){
                  db1= FirebaseDatabase.getInstance().getReference().child("user");
                  onResume();
              }
              else if(choosedaddress.equalsIgnoreCase("all")&&!choosdebloodgroup.equalsIgnoreCase("all")){
                  db1=FirebaseDatabase.getInstance().getReference().child("user").orderByChild("bloodgroup").equalTo(choosdebloodgroup);
                  onResume();
              }
              else if(!choosedaddress.equalsIgnoreCase("all")&&choosdebloodgroup.equalsIgnoreCase("all")){
                  db1=FirebaseDatabase.getInstance().getReference().child("user").orderByChild("address").equalTo(choosedaddress);
                  onResume();
              }
              else if(!choosedaddress.equalsIgnoreCase("all")&&!choosdebloodgroup.equalsIgnoreCase("all")){
                  db1=FirebaseDatabase.getInstance().getReference().child("user").orderByChild("bloodgroup_address").equalTo(choosedaddress+"_"+choosdebloodgroup);
                  onResume();
              }
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mydata.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                  String name=ds.child("name").getValue().toString();
                  String address=ds.child("address").getValue().toString();
                  String phone=ds.child("phone").getValue().toString();
                  String bloodgroup=ds.child("bloodgroup").getValue().toString();
                  String id=ds.child("id").getValue().toString();
                  SearchModule sm=new SearchModule();
                  sm.setName(name);
                  sm.setAddress(address);
                  sm.setPhone(phone);
                  sm.setId(id);
                  sm.setBloodgroup(bloodgroup);
                  mydata.add(sm);
                }
                lv.setAdapter(new BloodAdapter(getActivity(),mydata));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    private class AddressSpinner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            choosedaddress=address[i];

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
