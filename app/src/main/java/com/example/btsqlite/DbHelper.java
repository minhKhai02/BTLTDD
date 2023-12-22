package com.example.btsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo_database";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_TODO = "todo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TYPE = "type";

    private static final String CREATE_TABLE_TODO =
            "CREATE TABLE " + TABLE_TODO + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_CONTENT + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_TYPE + " TEXT" +
                    ")";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to upgrade the database, you can handle it here
        // Typically, this involves dropping the existing table and recreating it
        // You might also perform data migration or any other necessary tasks

        // Example:
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS ToDo");
            onCreate(db);
        }
    }
}