package com.magnit.testandroid.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ManagerDbImpl extends SQLiteOpenHelper implements ManagerDb {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    public ManagerDbImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(InfoDb.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(InfoDb.SQL_CREATE_HISTORY);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(InfoDb.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(InfoDb.SQL_DELETE_HISTORY);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    @Override
    public void addValue(Values value, String nameTable, String[] nameColumn) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn[0], value.getValue());
            contentValues.put(nameColumn[1], value.getRatio());
            db.insert(nameTable, null, contentValues);
            contentValues.clear();
            db.close();
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    @Override
    public void updateValue(Values value) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("ratio", value.getRatio());
            db.update("entries", contentValues, "value = ?", new String[]{String.valueOf(value.getValue())});
            contentValues.clear();
            db.close();
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    @Override
    public boolean existsRecords(String nameTable) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + nameTable, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    @Override
    public ArrayList<Values> getListValues(String nameTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Values> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM " + nameTable;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(new Values(cursor.getInt(1), cursor.getFloat(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrayList;
    }

    @Override
    public Values getValueByPosition(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        Values value = null;
        String sql = "SELECT * FROM entries WHERE id_main=" + position;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            value = new Values(cursor.getInt(1), cursor.getFloat(2));
        }
        cursor.close();
        db.close();
        return value;
    }
}
