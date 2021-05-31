package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

        final RadioGroup gender = (RadioGroup)findViewById(R.id.GenderRadioGroup);
        //int selectedId = gender.getCheckedRadioButtonId();
        final RadioButton maleSelected = (RadioButton) findViewById(R.id.MaleButton);
        final RadioButton femaleSelected = (RadioButton) findViewById(R.id.FemaleButton);
        final String[] donorGender = {null};




        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.MaleButton:
                        // do operations specific to this selection
                        donorGender[0] = "M";
                        break;
                    case R.id.FemaleButton:
                        // do operations specific to this selection
                        donorGender[0] = "F";
                        break;
                }
            }
        });


        final Button submit = (Button)findViewById(R.id.SubmitBtn);

        submit.setOnClickListener(v -> {


            if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || age.getText().toString().isEmpty() || donorGender[0] == null)
            {
                Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();
            }
            else
            {
                DonorDB.createNewDonor(name.getText().toString(),
                        donorGender[0],
                        Integer.parseInt(phone.getText().toString()),
                        Integer.parseInt(age.getText().toString()),
                        bloodType.getSelectedItem().toString(),
                        location.getSelectedItem().toString());

                name.getText().clear();
                phone.getText().clear();
                age.getText().clear();
                Toast.makeText(getApplicationContext(), "Donor Added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}