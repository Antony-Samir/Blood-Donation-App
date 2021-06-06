package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterDonorInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_donor_info);

        final DonorDBHelper DonorDB = new DonorDBHelper(this);

        EditText name = (EditText)findViewById(R.id.NameTxt);
        final EditText phone = (EditText)findViewById(R.id.PhoneTxt);
        final EditText age = (EditText)findViewById(R.id.AgeTxt);
        final EditText email = (EditText)findViewById(R.id.emailTxt);

        final Spinner location = (Spinner)findViewById(R.id.LocationSpinnerDonorRegister);

        final RadioGroup gender = (RadioGroup)findViewById(R.id.GenderRadioGroup);
        final String[] donorGender = {null};

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.MaleButton:
                        donorGender[0] = "M";
                        break;
                    case R.id.FemaleButton:
                        donorGender[0] = "F";
                        break;
                }
            }
        });


        final Button next = (Button)findViewById(R.id.submitBtn);

        next.setOnClickListener(v -> {


            if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || age.getText().toString().isEmpty() || email.getText().toString().isEmpty() || donorGender[0] == null)
            {
                Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();
            }
            else
            {
                DonorDB.createNewDonor(name.getText().toString(),
                        donorGender[0],
                        Integer.parseInt(phone.getText().toString()),
                        Integer.parseInt(age.getText().toString()),
                        email.getText().toString(),
                        location.getSelectedItem().toString());




                Toast.makeText(getApplicationContext(), "Please Complete Your Data", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(RegisterDonorInfoActivity.this, RegisterDonorHealthActivity.class);
                i.putExtra("donorName", name.getText().toString());

                startActivity(i);


                name.getText().clear();
                phone.getText().clear();
                age.getText().clear();
                email.getText().clear();
            }
        });

    }
}