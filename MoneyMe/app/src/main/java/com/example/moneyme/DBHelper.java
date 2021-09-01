package com.example.moneyme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Users.db";

    public DBHelper(Context context) {
        super(context, "Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table Users(username TEXT primary key, password TEXT, income TEXT, zero TEXT, thirty TEXT, sixty TEXT, hundred TEXT, savings TEXT, expences TEXT, free TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("income", "");
        contentValues.put("zero", "0");
        contentValues.put("thirty", "0");
        contentValues.put("sixty", "0");
        contentValues.put("hundred", "0");
        contentValues.put("savings", "0.40");
        contentValues.put("expences", "0.40");
        contentValues.put("free", "0.20");

        long result = MyDB.insert("Users", null, contentValues);
        MyDB.close();
        if(result == 1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        // check if there is such an object in the DB
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Users WHERE username = ?", new String[] {username});
        if(cursor.getCount() > 0){
            cursor.close();
            MyDB.close();
            return true;
        }
        else{
            cursor.close();
            MyDB.close();
            return false;
        }
    }

    public Boolean checkPassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        // check if there is an User with such a password and username
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?", new String[] {username, password});
        if(cursor.getCount() > 0){
            cursor.close();
            MyDB.close();
            return true;
        }
        else{
            cursor.close();
            MyDB.close();
            return false;
        }
    }

    public void insertIncome(String username, String password, String income){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?", new String[] {username, password});

        if(cursor.getCount() > 0){
            MyDB.execSQL("UPDATE Users SET income = ? WHERE username = ? AND password = ?;", new String[] {income, username, password});
        }
        cursor.close();
        MyDB.close();
    }

    public void voteEmoji(int index, String attribute){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        if(c.moveToFirst()){
            int result = Integer.parseInt(c.getString(index));
            String newResult = String.valueOf(result + 1);

            MyDB.execSQL("UPDATE Users SET " + attribute + " = ? WHERE " + attribute + " = ?;", new String[] {newResult, String.valueOf(result)});
        }
        c.close();
        MyDB.close();
    }

}


















