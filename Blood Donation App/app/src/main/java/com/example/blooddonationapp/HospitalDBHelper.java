package com.example.blooddonationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HospitalDBHelper extends SQLiteOpenHelper
{
    private static String databaseName = "hospitalDatabase";
    SQLiteDatabase hospitalDatabase;

    public HospitalDBHelper(Context context)
    {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table hospital(id integer primary key, " +
                "name text," +
                "phone integer," +
                "email text," +
                "password text," +
                "location text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists hospital");
        onCreate(db);
    }

    public void createNewHospital(String name, int phone, String email, String password, String location)
    {
        ContentValues row = new ContentValues();
        row.put("name", name);
        row.put("phone", phone);
        row.put("email", email);
        row.put("password", password);
        row.put("location", location);
        hospitalDatabase = getWritableDatabase();
        hospitalDatabase.insert("hospital", null, row);
        hospitalDatabase.close();
    }

    public Cursor fetchAllHospitals()
    {//Gets all Hospitals
        hospitalDatabase = getReadableDatabase();
        String[] rowDetails = {"id", "name", "phone", "email", "password", "location"};
        Cursor cursor = hospitalDatabase.query("hospital", rowDetails, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        hospitalDatabase.close();
        return cursor;
    }

    public String getHospitalPhone(String name)
    {
        hospitalDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = hospitalDatabase.rawQuery("Select phone from hospital where name like ?", arg);
        cursor.moveToFirst();
        hospitalDatabase.close();
        return cursor.getString(0);
    }

    public String getHospitalEmail(String name)
    {
        hospitalDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = hospitalDatabase.rawQuery("Select email from hospital where name like ?", arg);
        cursor.moveToFirst();
        hospitalDatabase.close();
        return cursor.getString(0);
    }

    public String getHospitalPassword(String name)
    {
        hospitalDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = hospitalDatabase.rawQuery("Select password from hospital where name like ?", arg);
        cursor.moveToFirst();
        hospitalDatabase.close();
        return cursor.getString(0);
    }

    public String getHospitalLocation(String name)
    {
        hospitalDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = hospitalDatabase.rawQuery("Select location from hospital where name like ?", arg);
        cursor.moveToFirst();
        hospitalDatabase.close();
        return cursor.getString(0);
    }

}
