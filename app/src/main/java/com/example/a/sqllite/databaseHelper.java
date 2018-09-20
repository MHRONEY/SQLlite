package com.example.a.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String TABLE_NAME = "mytable";
    private static final String ID = "id";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String GENDER = "gender";
    private static final int version = 1;

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String quary;
        quary = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY, " + USER_NAME + " VARCHAR(255) ," + PASSWORD + " VARCHAR(225)," + GENDER + " VARCHAR(225));";

        db.execSQL(quary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long insertdata(String id, String name, String password, String gender) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(USER_NAME, name);
        contentValues.put(PASSWORD, password);
        contentValues.put(GENDER, gender);

        long rowid = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowid;
    }


    public Cursor display() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor res;
        res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;


    }

    public boolean update(String mid, String name, String password, String gender) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, mid);
        contentValues.put(USER_NAME, name);
        contentValues.put(PASSWORD, password);
        contentValues.put(GENDER, gender);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id =?", new String[]{mid});
        return true;


    }

    public int delete(String mid) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "id =?", new String[]{mid});


    }


}


