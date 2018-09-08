package com.example.de.bloodbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button login, register;
    EditText email, password;
    FirebaseAuth auth;
    DatabaseReference db;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        email = findViewById(R.id.uemail);
        register = findViewById(R.id.register);
        password = findViewById(R.id.upassword);
        dialog=new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        db = FirebaseDatabase.getInstance().getReference().child("user");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final DataModule m = new DataModule();
                String myemail = email.getText().toString();
                String mypassword = password.getText().toString();
                if (TextUtils.isEmpty(myemail) || TextUtils.isEmpty(mypassword)) {
                    Toast.makeText(MainActivity.this, "Empty Username or Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    dialog.show();
                auth.signInWithEmailAndPassword(myemail, mypassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        SharedPreferences sp1 = getSharedPreferences("yourfile", Context.MODE_PRIVATE);//for saving login session
                        SharedPreferences.Editor editor = sp1.edit();//shared preferance lai edit garna lai editor banako
                        editor.putBoolean("state", true);//login state lai true gareko
                        editor.commit();
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, DashBoard.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login Faliure", Toast.LENGTH_SHORT).show();

                    }
                });}


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("yourfile", Context.MODE_PRIVATE);
        boolean state = sp.getBoolean("state", false);
        if (state) {
            Intent i = new Intent(MainActivity.this, DashBoard.class);
            startActivity(i);
        }
    }
}

