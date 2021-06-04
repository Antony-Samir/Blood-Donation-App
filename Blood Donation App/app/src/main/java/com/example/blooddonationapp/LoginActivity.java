package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button hospital = (Button)findViewById(R.id.HospitalBtn);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HospitalHomeActivity.class);
                startActivity(i);
            }
        });

        Button login = (Button)findViewById(R.id.LoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, DonorHomeActivity.class);
                startActivity(i);
            }
        });

        TextView newAccount = (TextView)findViewById(R.id.NewAccountBtn);
        newAccount.setOnClickListener(v -> {

            Intent webIntent = new Intent(LoginActivity.this, RegisterDonorInfoActivity.class);
            startActivity(webIntent);
        });
    }
}