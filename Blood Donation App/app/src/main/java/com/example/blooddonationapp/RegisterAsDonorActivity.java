package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterAsDonorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_signup);

        final DonorDBHelper DonorDB = new DonorDBHelper(this);

        EditText name = (EditText)findViewById(R.id.NameTxt);
        final EditText phone = (EditText)findViewById(R.id.PhoneTxt);
        final EditText age = (EditText)findViewById(R.id.AgeTxt);
        final Spinner bloodType = (Spinner)findViewById(R.id.BloodTypeSpinner2);
        final Spinner location = (Spinner)findViewById(R.id.LocationSpinner2);





        final Button submit = (Button)findViewById(R.id.SubmitBtn);

        submit.setOnClickListener(v -> {

            if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || age.getText().toString().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();
            }
            else
            {
                DonorDB.createNewDonor(name.getText().toString(), Integer.parseInt(phone.getText().toString()), Integer.parseInt(age.getText().toString()), bloodType.getSelectedItem().toString(), location.getSelectedItem().toString());
                name.getText().clear();
                phone.getText().clear();
                age.getText().clear();
                Toast.makeText(getApplicationContext(), "Donor Added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}