package com.example.blooddonationapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DonorDBHelper extends SQLiteOpenHelper {
    private static String databaseName = "donorDatabase";
    SQLiteDatabase donorDatabase;

    public DonorDBHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table donor(id integer primary key, " +
                " name text not null, gender text not null, phone int, age integer, bloodType text, location text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists movie");
        onCreate(db);
    }

    public void createNewDonor(String name, String gender, int phone, int age, String bloodType, String location)
    {
        ContentValues row = new ContentValues();
        row.put("name", name);
        row.put("gender", gender);
        row.put("phone", phone);
        row.put("age", age);
        row.put("bloodType", bloodType);
        row.put("location", location);
        donorDatabase = getWritableDatabase();
        donorDatabase.insert("donor", null, row);
        donorDatabase.close();
    }

    public Cursor fetchAllDonors()
    {
        donorDatabase = getReadableDatabase();
        String[] rowDetails = {"id", "name", "gender", "phone", "age", "bloodType", "location"};
        Cursor cursor = donorDatabase.query("donor", rowDetails, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        donorDatabase.close();
        return cursor;
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

    public String getDonorGender(String name)
    {
        donorDatabase = getReadableDatabase();
        String[] arg = {name};

        Cursor cursor = donorDatabase.rawQuery("Select gender from donor where name like ?", arg);
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

    public void updateDonor(String oldName, String newName, String newPhone, int newAge, String newBloodType, String newLocation)
    {
        donorDatabase = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("name", newName);
        row.put("phone", newPhone);
        row.put("age", newAge);
        row.put("bloodType", newBloodType);
        row.put("location", newLocation);
        donorDatabase.update("donor", row, "name like?", new String[]{oldName});
        donorDatabase.close();
    }

}
