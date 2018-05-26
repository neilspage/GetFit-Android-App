package com.example.michelle.useraccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_FOOD_ACTIVITY = "nutrition_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "food_activity";
    private static final String COL3 = "carbohydrates";
    private static final String COL4 = "fat";
    private static final String COL5 = "protein";
    private static final String COL6 = "sugar";
    private static final String COL7 = "energy";

    public DatabaseHelper(Context context) {
        super(context, TABLE_FOOD_ACTIVITY, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_FOOD_ACTIVITY
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, "
                + COL3 + " INTEGER DEFAULT 0, "
                + COL4 + " INTEGER DEFAULT 0, "
                + COL5 + " INTEGER DEFAULT 0, "
                + COL6 + " INTEGER DEFAULT 0, "
                + COL7 + " INTEGER DEFAULT 0)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_ACTIVITY);
        onCreate(db);
    }

    public boolean addData(String item, int carbohydrates, int fat, int protein, int sugar, int energy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, carbohydrates);
        contentValues.put(COL4, fat);
        contentValues.put(COL5, protein);
        contentValues.put(COL6, sugar);
        contentValues.put(COL7, energy);

        long result = db.insert(TABLE_FOOD_ACTIVITY, null, contentValues);

        return result != -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FOOD_ACTIVITY;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String foodActivity) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_FOOD_ACTIVITY + " WHERE " + COL2 + " = '" + foodActivity + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateFoodActivity(String newFoodActivity, int id, String oldFoodActivity,
                                   int newCarbohydrates, int oldCarbohydrates,
                                   int newFat, int oldFat,
                                   int newProtein, int oldProtein,
                                   int newSugar, int oldSugar,
                                   int newEnergy, int oldEnergy) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_FOOD_ACTIVITY
                + " SET " + COL2 + " = '" + newFoodActivity + "' WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL2 + " = '" + oldFoodActivity + "'";
        String query2 = "UPDATE " + TABLE_FOOD_ACTIVITY
                + " SET " + COL3 + " = '" + newCarbohydrates + "' WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL3 + " = '" + oldCarbohydrates + "'";
        String query3 = "UPDATE " + TABLE_FOOD_ACTIVITY
                + " SET " + COL4 + " = '" + newFat + "' WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL4 + " = '" + oldFat + "'";
        String query4 = "UPDATE " + TABLE_FOOD_ACTIVITY
                + " SET " + COL5 + " = '" + newProtein + "' WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL5 + " = '" + oldProtein + "'";
        String query5 = "UPDATE " + TABLE_FOOD_ACTIVITY
                + " SET " + COL6 + " = '" + newSugar + "' WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL6 + " = '" + oldSugar + "'";
        String query6 = "UPDATE " + TABLE_FOOD_ACTIVITY
                + " SET " + COL7 + " = '" + newEnergy + "' WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL7 + " = '" + oldEnergy + "'";
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
    }

    public void deleteFoodActivity(int id, String foodActivity) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_FOOD_ACTIVITY + " WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL2 + " = '" + foodActivity + "'";
        db.execSQL(query);
    }

    public void deleteAllFoodActivity() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_FOOD_ACTIVITY);
    }
}
