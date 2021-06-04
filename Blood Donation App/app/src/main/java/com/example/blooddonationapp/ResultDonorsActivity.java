package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResultDonorsActivity extends AppCompatActivity {

    ListView viewList;
    ArrayAdapter<String> viewAdapter;
    DonorDBHelper donorDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_donors);

        viewList = (ListView) findViewById(R.id.ResultList);
        viewAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        viewList.setAdapter(viewAdapter);

        donorDB = new DonorDBHelper(getApplicationContext());


        String bloodTypeExtra = getIntent().getExtras().getString("bloodType");
        String locationExtra = getIntent().getExtras().getString("location");

        Cursor cursor = donorDB.fetchAllDonors();
        while (!cursor.isAfterLast()) {
            if (locationExtra.equals(cursor.getString(6)) &&
                    bloodTypeExtra.equals(cursor.getString(9))) {
                viewAdapter.add(cursor.getString(1));
            }
            cursor.moveToNext();
        }

        if (viewAdapter.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No Donors to Show", Toast.LENGTH_SHORT).show();
        }

        viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phone = donorDB.getDonorPhone(((TextView) view).getText().toString()).toString();
                //Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_SHORT).show();
                Uri number = Uri.parse("tel:" + phone);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

            }
        });


    }
}