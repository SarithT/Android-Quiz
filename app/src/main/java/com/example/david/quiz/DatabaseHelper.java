package com.example.david.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by david on 26.11.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Quiz";
    private static final String DATABASE_TABLE_NAME = "Results";
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORY = "kategoria";
    private static final String KEY_DIFFICULTY = "poziom_trudności";
    private static final String KEY_NUMBER = "liczba_pytań";
    private static final String KEY_SCORE = "wynik";
    private SQLiteDatabase dbase;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CATEGORY
                + " TEXT, " + KEY_DIFFICULTY+ " TEXT, "+ KEY_NUMBER +" TEXT, "
                + KEY_SCORE +" TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addResult(Result result) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, result.getCategory());
        values.put(KEY_DIFFICULTY, result.getDifficulty());
        values.put(KEY_NUMBER, result.getNumber());
        values.put(KEY_SCORE, result.getScore());

        db.insert(DATABASE_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<Result> getAllResults(){
        ArrayList<Result> resultList = new ArrayList<Result>();
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Result result = new Result(
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)));
                result.setId(Integer.parseInt(cursor.getString(0)));
                resultList.add(result);
            } while (cursor.moveToNext());
        }

        cursor.close();
        Collections.reverse(resultList);
        return resultList;
    }

    public void clearResults(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
    }
}
