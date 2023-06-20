package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelperK extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "KORZINA";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String PRICE = "PRICE";
    private static final int DATABASE_VERSION = 1;

    public DbHelperK(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT," + PRICE + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewInsult(String name,Integer price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(PRICE, price);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public List<ObjectForKorzina> getKorzina() {
        List<ObjectForKorzina> listKorzina = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT DISTINCT "+NAME+", "+PRICE+ " FROM " + TABLE_NAME ;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
            int k=0;
            while (c.isAfterLast() == false) {
                @SuppressLint("Range")
                ObjectForKorzina objectForKorzina=new ObjectForKorzina(k
                        ,c.getString(c.getColumnIndex(NAME)),c.getInt(c.getColumnIndex(PRICE)));
                listKorzina.add(objectForKorzina);
                c.moveToNext();
            }
        }
        return listKorzina;
    }
    public int ColObjects(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT *  FROM "+ TABLE_NAME+ " WHERE "+NAME+"='"+name+"'";
        Cursor c = db.rawQuery(selectQuery, null);
        int count=0;
        if (c != null) {
            count = c.getCount();
        }
        return count;
    }
    @SuppressLint("Range")
    public int Sum(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME ;
        Cursor c = db.rawQuery(selectQuery, null);
        int sum=0;
        if (c != null) {
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                sum+=c.getInt(c.getColumnIndex(PRICE));
                c.moveToNext();
            }
        }
        return sum;
    }

    @SuppressLint("Range")
    public void deleteRow(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME ;
        Cursor c = db.rawQuery(selectQuery, null);
        int id=0;
        if (c != null) {
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                id=c.getInt(c.getColumnIndex(ID));
                c.moveToNext();
            }
        }
        db.delete(TABLE_NAME,ID+"="+id,null);
    }
    public void deleteAllRow(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME,NAME+"= '"+name+"'",null);
    }
    public void deleteAllRow()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME,null,null);
    }

}
