package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterDonorHealthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_donor_health);

        final DonorDBHelper DonorDB = new DonorDBHelper(this);


        String donorName = getIntent().getExtras().getString("donorName");
        final Spinner bloodType = (Spinner)findViewById(R.id.BloodTypeSpinner2);


        final RadioGroup diabetic = (RadioGroup)findViewById(R.id.diabeticRadioGroup);
        final String[] diabeticResult = {null};
        diabetic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.isDiabetic:
                        diabeticResult[0] = "Y";
                        break;
                    case R.id.isNotDiabetic:
                        diabeticResult[0] = "N";
                        break;
                }
            }
        });



        final RadioGroup bloodPressure = (RadioGroup)findViewById(R.id.bloodPressureRadioGroup);
        final String[] bloodPressureResult = {null};
        bloodPressure.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.isHighBloodPressure:
                        bloodPressureResult[0] = "Y";
                        break;
                    case R.id.isNotHighBloodPressure:
                        bloodPressureResult[0] = "N";
                        break;
                }
            }
        });



        final Button submit = (Button)findViewById(R.id.submitBtn);

        submit.setOnClickListener(v -> {

            if (diabeticResult[0] == null || bloodPressureResult[0] == null)
            {
                Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();
            }
            else
            {
                DonorDB.updateDonorHealth(
                        donorName,
                        diabeticResult[0],
                        bloodPressureResult[0],
                        bloodType.getSelectedItem().toString());


                Toast.makeText(getApplicationContext(), "Your Data Stored Successfully!", Toast.LENGTH_LONG).show();

                Intent i = new Intent(RegisterDonorHealthActivity.this, InformationActivity.class);
                startActivity(i);
            }
        });

    }
}