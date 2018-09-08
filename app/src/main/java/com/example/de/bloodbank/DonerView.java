package com.example.de.bloodbank;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DonerView extends AppCompatActivity {
    TextView passname, passaddress, passphone, passblood, call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        call = findViewById(R.id.call);
        passname = findViewById(R.id.viewname);
        passaddress = findViewById(R.id.viewaddress);
        passphone = findViewById(R.id.viewphone);
        passblood = findViewById(R.id.viewbloodgroup);
        String sentname = getIntent().getExtras().getString("name");
        String sentaddress = getIntent().getExtras().getString("address");
        String sentphone = getIntent().getExtras().getString("phone");
        String sentbloodgroup = getIntent().getExtras().getString("bloodgroup");
        passname.setText(sentname);
        passaddress.setText(sentaddress);
        passphone.setText(sentphone);
        passblood.setText(sentbloodgroup);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DonerView.this, "Calling" + passphone.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", passphone.getText().toString(), null));
                if (ActivityCompat.checkSelfPermission(DonerView.this
                        , android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

    }
}
