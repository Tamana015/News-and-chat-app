 package com.example.chaayas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

public class dbhelpher extends SQLiteOpenHelper {
public dbhelpher( Context context)
{
    super(context, "Login.db",null,1);
}
@Override
public void onCreate(SQLiteDatabase myDB)
{
    myDB.execSQL("create Table users(email Text ,username Text primary key, password Text)");
}

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
    myDB.execSQL("drop Table if exists users");
    }
    public Boolean insertData(String email, String username, String password)
    {
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result= myDB.insert("users",null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Boolean  checkusername(String username)
    {
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("select * from users where username =?", new String[]{username});
    if(cursor.getCount()>0)
    {
        return true;
    }
    else
    {
        return false;
    }
    }
    public  Boolean checkusernamePassword(String username, String password)
    {
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("select * from users where username = ? and password =?" , new String[]{username,password});
    if(cursor.getCount()>0)
    {
        return true;
    }
    else
    {
        return false;
    }
    }


}
