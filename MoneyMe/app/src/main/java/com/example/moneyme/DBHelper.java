package com.example.moneyme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Users.db";

    public DBHelper(Context context) {
        super(context, "Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MYdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MYdb.insert("users", null, contentValues);
        if(result == 1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        // check if there is such an object in the DB
        Cursor cursor = MYDB.rawQuery("select * FROM users WHERE username = ?", new String[] {username});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkPassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        // check if there is an User with such a password and username
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[] {username, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}


















