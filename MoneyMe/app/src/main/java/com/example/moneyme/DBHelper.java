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
        contentValues.put("savings", "40");
        contentValues.put("expences", "40");
        contentValues.put("free", "20");

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

    public String getIncome(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(2);
        }
        c.close();
        MyDB.close();
        return result;
    }

    public String getUsername(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(0);
        }
        c.close();
        MyDB.close();
        return result;
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

    public String getVerySatisfied(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(6);
        }
        c.close();
        MyDB.close();
        return result;
    }

    public String getSatisfied(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(5);
        }
        c.close();
        MyDB.close();
        return result;
    }

    public String getDissatisfied(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(4);
        }
        c.close();
        MyDB.close();
        return result;
    }

    public String getVeryDissatisfied(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(3);
        }
        c.close();
        MyDB.close();
        return result;
    }

    public void changePercentage(String username, String percentageSave, String percentageExp, String percentageFree){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        if(c.moveToFirst()){
            //double save = Double.parseDouble(c.getString(7));
            MyDB.execSQL("UPDATE Users SET savings = ? WHERE username = ?" , new String[] {percentageSave, username});
            //MyDB.execSQL("UPDATE Users SET savings = ? WHERE savings = ? AND username = ?" , new String[] {percentageSave, String.valueOf(save), username});


            //double exp = Double.parseDouble(c.getString(8));
            MyDB.execSQL("UPDATE Users SET expences = ? WHERE username = ?", new String[] {percentageExp, username});
            //MyDB.execSQL("UPDATE Users SET expences = ? WHERE expences = ? AND username = ?", new String[] {percentageExp, String.valueOf(exp), username});


            //double free = Double.parseDouble(c.getString(9));
            MyDB.execSQL("UPDATE Users SET free = ? WHERE username = ?", new String[] {percentageFree, username});
            //MyDB.execSQL("UPDATE Users SET free = ? WHERE free = ? AND username = ?", new String[] {percentageFree, String.valueOf(free), username});
        }
        c.close();
        MyDB.close();
    }

    public String getSavings(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(7);
        }
        c.close();
        MyDB.close();
        return result;
    }

    public String getExpences(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(8);
        }
        c.close();
        MyDB.close();
        return result;
    }

    public String getFree(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("SELECT * FROM Users", null);

        String result = "";
        if(c.moveToFirst()){
            result = c.getString(9);
        }
        c.close();
        MyDB.close();
        return result;
    }
}


















