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

    public static final String TABLE_NAME_PERSON = "PERSON";
    public static final String ID_PERSON = "ID";
    public static final String PERSON_NAME="NAME";
    public static final String PERSON_F_NAME="FNAME";
    public static final String PERSON_S_NAME="SNAME";
    public static final String PERSON_AGE="AGE";
    public static final String PERSON_POL="POL";
    public static final String PERSON_FOTO="FOTO";

    public DbHelperK(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT," + PRICE + " INTEGER)";
        db.execSQL(query);

        String query1="CREATE TABLE " + TABLE_NAME_PERSON + " ("
                + ID_PERSON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PERSON_NAME + " TEXT,"+ PERSON_F_NAME + " TEXT,"+ PERSON_S_NAME + " TEXT,"+ PERSON_AGE +
                " TEXT,"+ PERSON_POL + " TEXT," + PERSON_FOTO + " BLOB)";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PERSON);
        onCreate(db);
    }

    public void addNewObject(String name,Integer price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(PRICE, price);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }
    public void addNewPerson(String name, String firstName, String secondNAme,
                             String age, String pol,byte[] foto) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PERSON_NAME, name);
        values.put(PERSON_F_NAME, firstName);
        values.put(PERSON_S_NAME, secondNAme);
        values.put(PERSON_AGE, age);
        values.put(PERSON_POL, pol);
        values.put(PERSON_FOTO, foto);

        db.insert(TABLE_NAME_PERSON, null, values);

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
                k++;
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
    public void deletePerson(String name,String fName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME_PERSON,PERSON_NAME+"= '"+name+"',"+PERSON_F_NAME+"= '"+fName+"'",null);
    }
    public void deleteAllPerson()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME_PERSON,null,null);
    }
    public void deleteAllRow()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME,null,null);
    }
    public CardPacient getPacient() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * "+ " FROM " + TABLE_NAME_PERSON ;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();

            while (c.isAfterLast() == false) {
                @SuppressLint("Range")
                CardPacient person=new CardPacient(c.getString(c.getColumnIndex(PERSON_NAME)),
                        c.getString(c.getColumnIndex(PERSON_F_NAME)),c.getString(c.getColumnIndex(PERSON_S_NAME)),
                        c.getString(c.getColumnIndex(PERSON_AGE)),c.getString(c.getColumnIndex(PERSON_POL)),c.getBlob(c.getColumnIndex(PERSON_FOTO)));
                return person;
            }
        }
        return null;
    }

}
