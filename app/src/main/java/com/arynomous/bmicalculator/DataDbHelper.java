package com.arynomous.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BMICalculator.db";
    private String TABLE_NAME = "Data";
    private SQLiteDatabase db = this.getWritableDatabase();

    public DataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        db.execSQL("CREATE TABLE "+ TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT, history VARCHAR )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME );
    }

    public void addData(String masukan){
        ContentValues cv = new ContentValues();
        cv.put("history", masukan);
        db.insert(TABLE_NAME, null, cv);
    }

    public List<String> getData() {
        List<String> dataList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                int historyIndex = c.getColumnIndex("history");
                dataList.add(c.getString(historyIndex));
            }while (c.moveToNext());
        }

        c.close();
        return dataList;
    }

    public List<Integer> getID() {
        List<Integer> dataList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                int index = c.getColumnIndex("id");
                dataList.add(c.getInt(index));
            }while (c.moveToNext());
        }

        c.close();
        return dataList;
    }

    public void deleteData(int id) {
        db.delete(TABLE_NAME, "id = ?",new String[]{Integer.toString(id)} );
    }
}
