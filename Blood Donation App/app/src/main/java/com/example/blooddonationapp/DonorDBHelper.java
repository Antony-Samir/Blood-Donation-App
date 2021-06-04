package com.example.blooddonationapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DonorDBHelper extends SQLiteOpenHelper {
    private static String databaseName = "donorDatabase";
    SQLiteDatabase donorDatabase;

    public DonorDBHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table donor(id integer primary key, " +
                "name text," +
                "gender text," +
                "phone integer," +
                "age integer," +
                "email text," +
                "location text," +
                "isDiabetic integer," +
                "isHighBloodPressure text," +
                "bloodType text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists movie");
        onCreate(db);
    }

    public void createNewDonor(String name, String gender, int phone, int age, String email, String location)
    {
        ContentValues row = new ContentValues();
        row.put("name", name);
        row.put("gender", gender);
        row.put("phone", phone);
        row.put("age", age);
        row.put("email", email);
        row.put("location", location);
        donorDatabase = getWritableDatabase();
        donorDatabase.insert("donor", null, row);
        donorDatabase.close();
    }

    public void updateDonorHealth(String oldName, String isDiabetic, String isHighBloodPressure, String bloodType)
    {
        donorDatabase = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("isDiabetic", isDiabetic);
        row.put("isHighBloodPressure", isHighBloodPressure);
        row.put("bloodType", bloodType);
        donorDatabase.update("donor", row, "name like?", new String[]{oldName});
        donorDatabase.close();
    }

    public Cursor fetchAllDonors()
    {
        donorDatabase = getReadableDatabase();
        String[] rowDetails = {"id", "name", "gender", "phone", "age", "email", "location", "isDiabetic", "isHighBloodPressure", "bloodType"};
        Cursor cursor = donorDatabase.query("donor", rowDetails, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        donorDatabase.close();
        return cursor;
    }

    public String getDonorGender(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select gender from donor where name like ?", arg);
        cursor.moveToFirst();
        donorDatabase.close();
        return cursor.getString(0);
    }

    public String getDonorPhone(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select phone from donor where name like ?", arg);
        cursor.moveToFirst();
        donorDatabase.close();
        return cursor.getString(0);
    }

    public String getDonorAge(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select age from donor where name like ?", arg);
        cursor.moveToFirst();
        donorDatabase.close();
        return cursor.getString(0);
    }

    public String getDonorEmail(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select email from donor where name like ?", arg);
        cursor.moveToFirst();
        donorDatabase.close();
        return cursor.getString(0);
    }

    public String getDonorLocation(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select location from donor where name like ?", arg);
        cursor.moveToFirst();
        donorDatabase.close();
        return cursor.getString(0);
    }

    public String getDonorIsDiabetic(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select isDiabetic from donor where name like ?", arg);
        cursor.moveToFirst();
        donorDatabase.close();
        return cursor.getString(0);
    }

    public String getDonorIsHighBloodPressure(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select isHighBloodPressure from donor where name like ?", arg);
        cursor.moveToFirst();
        donorDatabase.close();
        return cursor.getString(0);
    }

    public String getDonorBloodType(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select bloodType from donor where name like ?", arg);
        cursor.moveToFirst();
        donorDatabase.close();
        return cursor.getString(0);
    }

    public void deleteDonor(String name)
    {
        donorDatabase = getWritableDatabase();
        donorDatabase.delete("donor", "name='" + name + "'", null);
        donorDatabase.close();
    }

}
