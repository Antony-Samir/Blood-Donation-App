package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterHospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_hospital);

        final HospitalDBHelper HospitalDB = new HospitalDBHelper(this);

        EditText hospitalName = (EditText)findViewById(R.id.hospitalNameTxt);
        EditText hospitalPhone = (EditText)findViewById(R.id.hospitalPhoneTxt);
        EditText hospitalEmail = (EditText)findViewById(R.id.hospitalEmailTxt);
        EditText hospitalPassword = (EditText)findViewById(R.id.hospitalPasswordTxt);
        final Spinner hospitalLocation = (Spinner)findViewById(R.id.LocationSpinnerHospitalSignup);


        final Button submit = (Button)findViewById(R.id.hospitalSubmitBtn);
        submit.setOnClickListener(v -> {

            if (hospitalName.getText().toString().isEmpty() || hospitalPhone.getText().toString().isEmpty() || hospitalEmail.getText().toString().isEmpty() || hospitalPassword.getText().toString().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();
            }
            else {
                HospitalDB.createNewHospital(
                        hospitalName.getText().toString(),
                        Integer.parseInt(hospitalPhone.getText().toString()),
                        hospitalEmail.getText().toString(),
                        hospitalPassword.getText().toString(),
                        hospitalLocation.getSelectedItem().toString()
                );

                //Toast.makeText(getApplicationContext(), hospitalName.getText().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), hospitalPhone.getText().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), hospitalEmail.getText().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), hospitalPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), hospitalLocation.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "Registration Completed Successfully!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegisterHospitalActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }
}