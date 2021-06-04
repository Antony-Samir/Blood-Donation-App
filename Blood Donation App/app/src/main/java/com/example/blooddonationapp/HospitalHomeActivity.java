package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class HospitalHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_home);

        final Spinner bloodType = (Spinner)findViewById(R.id.BloodTypeSpinner);
        final Spinner location = (Spinner)findViewById(R.id.LocationSpinner);


        Button findDonor = (Button)findViewById(R.id.findDonorBtn);
        Button beDonor = (Button)findViewById(R.id.beDonorBtn);
        Button information = (Button)findViewById(R.id.infoBtn);


        Button fetchAllDonors = (Button)findViewById(R.id.fetchAllBtn);
        fetchAllDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HospitalHomeActivity.this, AllDonorsActivity.class);
                startActivity(i);
            }
        });



        findDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HospitalHomeActivity.this, ResultDonorsActivity.class);
                i.putExtra("bloodType", bloodType.getSelectedItem().toString());
                i.putExtra("location", location.getSelectedItem().toString());
                startActivity(i);
            }
        });

        beDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HospitalHomeActivity.this, RegisterDonorInfoActivity.class);
                startActivity(i);
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HospitalHomeActivity.this, InformationActivity.class);
                startActivity(i);
            }
        });
    }
}