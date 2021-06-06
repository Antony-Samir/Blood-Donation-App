package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final HospitalDBHelper HospitalDB = new HospitalDBHelper(this);




        EditText userName = (EditText)findViewById(R.id.UsernameTxt);
        EditText password = (EditText)findViewById(R.id.PasswordTxt);


        Button login = (Button)findViewById(R.id.LoginBtn);
        login.setOnClickListener(v -> {
            if (userName.getText().toString().equals(HospitalDB.getHospitalEmail("Ain Shams Uni")) && password.getText().toString().equals(HospitalDB.getHospitalPassword("Ain Shams Uni")))
            {//Check for the hospital account

                //Correct Email: "test@gmail.com"
                //Correct Password: 123
                Intent i = new Intent(LoginActivity.this, HospitalHomeActivity.class);
                startActivity(i);
                userName.getText().clear();
                password.getText().clear();
            }
            else
            {

                Toast.makeText(getApplicationContext(), "Username or Password is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        TextView newDonorAccount = (TextView)findViewById(R.id.NewDonorAccountBtn);
        newDonorAccount.setOnClickListener(v -> {//go to new donor activity

            Intent i = new Intent(LoginActivity.this, RegisterDonorInfoActivity.class);
            startActivity(i);
        });

        TextView newHospitalAccount = (TextView)findViewById(R.id.NewHospitalAccountBtn);
        newHospitalAccount.setOnClickListener(v -> {//go to new hospital activity

            Intent i = new Intent(LoginActivity.this, RegisterHospitalActivity.class);
            startActivity(i);
        });
    }
}