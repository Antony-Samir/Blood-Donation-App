package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean firstTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        firstTime = sharedPreferences.getBoolean("firstTime", true);

        if (firstTime)
        {//To show the splash screen for 3 seconds
            getSupportActionBar().hide();
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime", firstTime);
                    editor.apply();

                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);

            final HospitalDBHelper HospitalDB = new HospitalDBHelper(this);
            HospitalDB.createNewHospital("Ain Shams Uni", 01234, "test@gmail.com", "123", "Abassya");

            //Toast.makeText(getApplicationContext(), HospitalDB.getHospitalEmail("Ain Shams Uni"), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), HospitalDB.getHospitalPassword("Ain Shams Uni"), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), HospitalDB.getHospitalLocation("Ain Shams Uni"), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), HospitalDB.getHospitalPhone("Ain Shams Uni"), Toast.LENGTH_SHORT).show();

        }
        else
        {//Not the 1st time
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }



    }
}