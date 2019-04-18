package com.example.indo_asia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version){
        super(context,name,factory,version);

    }



    public void queryData(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public  void insertData(String name, String age, String phone, String emailid, String location, String DOB, String Height,
                           String weight, String Bust, String waist, String Hip,  byte[] image)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql =  "INSERT INTO Star VALUES(NULL, ?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1 , name);
        statement.bindString(2 , age);
        statement.bindString(3 , phone);
        statement.bindString(4,emailid);
        statement.bindString(5,location);
        statement.bindString(6 , DOB);
        statement.bindString(7 , Height);
        statement.bindString(8 , weight);
        statement.bindString(9,Bust);
        statement.bindString(10,waist);
        statement.bindString(11,Hip);

        statement.bindBlob(12 , image);

        statement.executeInsert();


    }

    public void updateData (String name, String age, String phone, String emailid, String location, String DOB,
                           String Height, String weight, String Bust, String waist, String Hip, byte[] image, int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Star SET name=?, age=?, phone=?, emailid=?, location = ?, DOB = ?, Height = ?, weight = ?, Bust = ?, waist = ?, Hip = ?, image=? where id=?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindDouble(0,(double)id);
        statement.bindString(1 , name);
        statement.bindString(2 , age);
        statement.bindString(3 , phone);
        statement.bindString(4,emailid);
        statement.bindString(5,location);
        statement.bindString(6 , DOB);
        statement.bindString(7 , Height);
        statement.bindString(8 , weight);
        statement.bindString(9,Bust);
        statement.bindString(10,waist);
        statement.bindString(11,Hip);
        statement.bindBlob(12, image);

        statement.executeInsert();
        database.close();
    }

    //deletedata

    public void deleteData (int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM Star WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();

    }

    public Cursor getData (String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}