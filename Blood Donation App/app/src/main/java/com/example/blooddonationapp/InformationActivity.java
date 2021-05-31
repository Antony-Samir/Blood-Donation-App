package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.TextView;

import java.util.Calendar;

import static java.net.Proxy.Type.HTTP;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        String infoText = "A blood donation occurs when a person voluntarily has blood drawn and used for transfusions and/or made into biopharmaceutical medications by a process called fractionation (separation of whole blood components). \n\n" +
                "Donation may be of whole blood, or of specific components directly (apheresis).\n\n" +
                "Blood banks often participate in the collection process as well as the procedures that follow it.";

        TextView infoTextView = (TextView) findViewById(R.id.DummyTextView);
        infoTextView.setText(infoText);


        //Visit Website
        TextView moreInfo = (TextView)findViewById(R.id.infoTxtView);
        moreInfo.setOnClickListener(v -> {

            Uri webpage = Uri.parse("https://en.wikipedia.org/wiki/Blood_donation");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        //Go to Location
        TextView location = (TextView)findViewById(R.id.locationTxtView);
        location.setOnClickListener(v -> {

            Uri webpage = Uri.parse("geo:30.078725483762756, 31.288135123930722");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        //Send Mail
        TextView mail = (TextView)findViewById(R.id.mailTxtView);
        mail.setOnClickListener(v -> {

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            // The intent does not have a URI, so declare the "text/plain" MIME type
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"hosp@asush.asu.edu.eg"}); // recipients
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reserve Appointment");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "I want to reserve an appointment at the hospital.");
            //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
            // You can also attach multiple items by passing an ArrayList of Uris
            startActivity(emailIntent);
        });

        //Make Event in Calendar
        TextView calendar = (TextView)findViewById(R.id.calendarTxtView);
        calendar.setOnClickListener(v -> {

            // Event is on January 23, 2021 -- from 7:30 AM to 10:30 AM.
            Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(2021, 5, 15, 7, 30);
            Calendar endTime = Calendar.getInstance();
            endTime.set(2021, 5, 15, 10, 30);
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
            calendarIntent.putExtra(CalendarContract.Events.TITLE, "Hospital Appointment");
            calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Ain Shams University Hospital");

            startActivity(calendarIntent);
        });

    }
}