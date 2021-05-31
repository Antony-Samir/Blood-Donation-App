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

public class AllDonorsActivity extends AppCompatActivity {

    ListView viewAllList;
    ArrayAdapter<String> viewAllAdapter;
    DonorDBHelper donorDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donors);

        viewAllList = (ListView)findViewById(R.id.testList);
        viewAllAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        viewAllList.setAdapter(viewAllAdapter);

        donorDB = new DonorDBHelper(getApplicationContext());


        Cursor cursor = donorDB.fetchAllDonors();
        while (!cursor.isAfterLast())
        {
            viewAllAdapter.add(cursor.getString(1));
            cursor.moveToNext();
        }

        viewAllList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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