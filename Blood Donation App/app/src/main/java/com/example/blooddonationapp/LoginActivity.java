package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText userName = (EditText)findViewById(R.id.UsernameTxt);
        EditText password = (EditText)findViewById(R.id.PasswordTxt);


        Button login = (Button)findViewById(R.id.LoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().equals("Hospital") && password.getText().toString().equals("123")) {
                    Intent i = new Intent(LoginActivity.this, HospitalHomeActivity.class);
                    startActivity(i);
                    userName.getText().clear();
                    password.getText().clear();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Username or Password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView newAccount = (TextView)findViewById(R.id.NewAccountBtn);
        newAccount.setOnClickListener(v -> {

            Intent webIntent = new Intent(LoginActivity.this, RegisterDonorInfoActivity.class);
            startActivity(webIntent);
        });
    }
}