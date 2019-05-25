package com.example.indo_asia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

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

    public  void insertData(String name, String emailid, String state, String country, String city, String DOB, String Height,
                            String weight, String Bust, String waist, String Hip, byte[] image, byte[] image1, byte[] image2)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql =  "INSERT INTO STM VALUES(NULL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1 , name);
        statement.bindString(2,emailid);
        statement.bindString(3,state);
        statement.bindString(4,country);
        statement.bindString(5,city);
        statement.bindString(6 , DOB);
        statement.bindString(7 , Height);
        statement.bindString(8 , weight);
        statement.bindString(9,Bust);
        statement.bindString(10,waist);
        statement.bindString(11,Hip);

        statement.bindBlob(12 , image);
        statement.bindBlob(13,image1);
        statement.bindBlob(14,image2);


        statement.executeInsert();


    }

    public void updateData (String name, String emailid, String state, String country, String city, String DOB, String Height,
                            String weight, String Bust, String waist, String Hip,  byte[] image, byte[] image1, byte[] image2, int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE STM SET name=?, emailid=?, state=?, country=?, city=?, DOB = ?, Height = ?, weight = ?, Bust = ?, waist = ?, Hip = ?, image=?, image1=?, image2=? where id=?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindDouble(0,(double)id);
        statement.bindString(1 , name);
        statement.bindString(2,emailid);
        statement.bindString(3,state);
        statement.bindString(4,country);
        statement.bindString(5,city);
        statement.bindString(6 , DOB);
        statement.bindString(7 , Height);
        statement.bindString(8 , weight);
        statement.bindString(9,Bust);
        statement.bindString(10,waist);
        statement.bindString(11,Hip);

        statement.bindBlob(12, image);
        statement.bindBlob(13,image1);
        statement.bindBlob(14,image2);

        statement.executeInsert();
        database.close();
    }

    //deletedata

    public void deleteData (int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM STM WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();

    }

    public Cursor getData(String sql)
    {
        String countQuery = "SELECT  * FROM STM";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.close();

        // return count

        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveName(String label) {

    }


    public List<String> getAllNames() {
        List<String> names = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM STM";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning names
        return names;
    }
}