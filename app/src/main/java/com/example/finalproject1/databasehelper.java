package com.example.finalproject1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasehelper extends SQLiteOpenHelper {


    public databasehelper(Context context) {
        super(context, "BMIDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE BMI (bmi_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, weight TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS BMI");
        onCreate(db);
    }

    public boolean insertItem(String date, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Date", date);
        cv.put("Weight", weight);

        long res = db.insert("BMI", null, cv);
        return res != -1;


    }



    // retrieve items from the table based on specific critera
    public Cursor getItemByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM BMI WHERE date = ?", new String[]{name});
    }

}

