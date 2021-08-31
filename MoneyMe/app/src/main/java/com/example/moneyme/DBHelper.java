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
        MyDB.execSQL("create Table Users(username TEXT primary key, password TEXT, income TEXT, zero TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MYdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("income", "");
        contentValues.put("zero", "0");

        long result = MYdb.insert("Users", null, contentValues);
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
        Cursor cursor = MYDB.rawQuery("SELECT * FROM Users WHERE username = ?", new String[] {username});
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
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?", new String[] {username, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void insertIncome(String username, String password, String income){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?", new String[] {username, password});

        if(cursor.getCount() > 0){
            MyDB.execSQL("REPLACE INTO Users(username, password, income) VALUES(?,?,?);", new String[] {username, password, income});
        }
    }

    public void voteZero(String username, String password, String income){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        if(c.moveToFirst()){
            int zero = Integer.parseInt(c.getString(3));
            String newZero = String.valueOf(zero + 1);

            MyDB.execSQL("REPLACE INTO Users(username, password, income, zero) VALUES(?,?,?,?);", new String[] {username, password, income, newZero});
        }
        c.close();
        MyDB.close();
    }


}


















