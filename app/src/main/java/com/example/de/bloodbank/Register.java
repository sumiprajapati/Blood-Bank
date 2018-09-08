package com.example.de.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Button register;
    EditText email,password,name,phone;
    TextView tv;
    FirebaseAuth auth;
    DatabaseReference db;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog=new ProgressDialog(Register.this);
        dialog.setMessage("Verifying User ...... Please Wait");
        dialog.setCancelable(false);
        auth= FirebaseAuth.getInstance();
        setContentView(R.layout.register);
        register=findViewById(R.id.register);
        email=findViewById(R.id.uemail);
        password=findViewById(R.id.upassword);
        name=findViewById(R.id.uname);
        phone=findViewById(R.id.uphone);
        db= FirebaseDatabase.getInstance().getReference().child("user");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DataModule m=new DataModule();
                final String myname=name.getText().toString();
                final String myphone=phone.getText().toString();
                String myemail=email.getText().toString();
                String mypassword=password.getText().toString();
                if (TextUtils.isEmpty(myemail) || TextUtils.isEmpty(mypassword)|| TextUtils.isEmpty(myname)|| TextUtils.isEmpty(myphone)) {
                    Toast.makeText(Register.this, "Empty Field Detected", Toast.LENGTH_SHORT).show();
                }
                else{
                    dialog.show();
                auth.createUserWithEmailAndPassword(myemail,mypassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
//                        myuid=authResult.getUser().getUid();
                        m.setId(authResult.getUser().getUid());
                        m.setName(myname);
                        m.setPhone(myphone);
                        m.setAddress("notset");
                        m.setBloodgroup("notset");
                        m.setDoner("notset");
                        m.setBloodgroup_address("notset_notset");
                        db.child(authResult.getUser().getUid()).setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                dialog.dismiss();
                                Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Register.this,MainActivity.class);
                                startActivity(i);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
//                        tv.setText(authResult.getUser().getUid());

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                });

            }}
        });
    }
}

