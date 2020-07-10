package com.example.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG_DbHelper = "DbHelper";
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "ChatDB";
    protected static final String TABLE_NAME = "Messages";
    protected static final String ID = "id";
    protected static final String MESSAGE = "message";
    protected static final String TYPE = "type";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " LONG PRIMARY KEY, " + MESSAGE + " TEXT, " + TYPE + " NUMBER ) ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Messages";
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM Messages";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    public void drop(MessageModel message) {

        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereArgs = new String[]{String.valueOf(message.getId())};
        db.delete(TABLE_NAME, whereClause, whereArgs);

    }

    public void create(MessageModel message) {


        ContentValues values = new ContentValues();
        values.put("id", (long) count()+1);
        values.put("message", message.getMessage());
        values.put("type", message.isSend());

        SQLiteDatabase db = this.getWritableDatabase();
        db.getPageSize();
        boolean createSuccessful = db.insert("Messages", null, values) > 0;
        db.close();

    }


    public List<MessageModel> read() {

        List<MessageModel> recordsList = new ArrayList<MessageModel>();

        String sql = "SELECT * FROM Messages ORDER BY id ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                long id = Long.parseLong(cursor.getString(cursor.getColumnIndex("id")));
                String messageEntry = cursor.getString(cursor.getColumnIndex("message"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                MessageModel message;
                if (type == 0) {
                    message = new MessageModel(id, messageEntry, true);
                } else {
                    message = new MessageModel(id, messageEntry, false);
                }


                recordsList.add(message);

            } while (cursor.moveToNext());
        }
        printCursor(cursor);
        cursor.close();
        db.close();

        return recordsList;
    }

    public void printCursor(Cursor c) {

        Log.i(TAG_DbHelper, "Database Version: " + DATABASE_VERSION);

        Log.i(TAG_DbHelper, "column count: " + c.getColumnCount());

        for (int i = 0; i < c.getColumnCount(); i++) {
            Log.i(TAG_DbHelper, "column " + i + ": " + c.getColumnName(i));
        }


        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Log.i(TAG_DbHelper, "Id: " + c.getString(c.getColumnIndex("id")));
                Log.i(TAG_DbHelper, "Message: " + c.getString(c.getColumnIndex("message")));
                Log.i(TAG_DbHelper, "Type: " + c.getString(c.getColumnIndex("type")));
                c.moveToNext();
            }
        }


        Log.i(TAG_DbHelper, String.valueOf(c.getColumnCount()));


    }
}