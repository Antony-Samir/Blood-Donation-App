package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AllDonorsActivity extends AppCompatActivity {

    ListView viewAllList;
    ArrayAdapter<String> viewAllAdapter;
    DonorDBHelper donorDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donors);

        final TextView genderResult = (TextView)findViewById(R.id.GenderResultTxt);
        final TextView phoneResult = (TextView)findViewById(R.id.PhoneResultTxt);
        final TextView ageResult = (TextView)findViewById(R.id.AgeResultTxt);
        final TextView emailResult = (TextView)findViewById(R.id.EmailResultTxt);
        final TextView locationResult = (TextView)findViewById(R.id.LocationResultTxt);
        final TextView diabeticResult = (TextView)findViewById(R.id.DiabeticResultTxt);
        final TextView highBloodPressureResult = (TextView)findViewById(R.id.BloodPressureResultTxt);
        final TextView bloodTypeResult = (TextView)findViewById(R.id.BloodTypeResultTxt);


        viewAllList = (ListView)findViewById(R.id.AllDonorsList);
        viewAllAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        viewAllList.setAdapter(viewAllAdapter);

        donorDB = new DonorDBHelper(getApplicationContext());


        Cursor cursor = donorDB.fetchAllDonors();
        while (!cursor.isAfterLast())
        {
            viewAllAdapter.add(cursor.getString(1));
            cursor.moveToNext();
        }

        if (viewAllAdapter.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "No Donors to Show", Toast.LENGTH_SHORT).show();
        }

        viewAllList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), ((TextView) view).getText().toString() + "'s Data", Toast.LENGTH_SHORT).show();


                genderResult.setText(donorDB.getDonorGender(((TextView) view).getText().toString()));
                phoneResult.setText(donorDB.getDonorPhone(((TextView) view).getText().toString()));
                ageResult.setText(donorDB.getDonorAge(((TextView) view).getText().toString()));
                emailResult.setText(donorDB.getDonorEmail(((TextView) view).getText().toString()));
                locationResult.setText(donorDB.getDonorLocation(((TextView) view).getText().toString()));
                diabeticResult.setText(donorDB.getDonorIsDiabetic(((TextView) view).getText().toString()));
                highBloodPressureResult.setText(donorDB.getDonorIsHighBloodPressure(((TextView) view).getText().toString()));
                bloodTypeResult.setText(donorDB.getDonorBloodType(((TextView) view).getText().toString()));

            }
        });
    }
}