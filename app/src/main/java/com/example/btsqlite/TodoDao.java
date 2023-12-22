package com.example.btsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TodoDao {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public TodoDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(ToDo todo) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_TITLE, todo.getTitle());
        values.put(DbHelper.COLUMN_CONTENT, todo.getContent());
        values.put(DbHelper.COLUMN_DATE, todo.getDate());
        values.put(DbHelper.COLUMN_TYPE, todo.getType());

        return database.insert(DbHelper.TABLE_TODO, null, values);
    }

    public int update(ToDo todo) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_TITLE, todo.getTitle());
        values.put(DbHelper.COLUMN_CONTENT, todo.getContent());
        values.put(DbHelper.COLUMN_DATE, todo.getDate());
        values.put(DbHelper.COLUMN_TYPE, todo.getType());

        return database.update(
                DbHelper.TABLE_TODO,
                values,
                DbHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(todo.getId())}
        );
    }

    public int delete(long todoId) {
        return database.delete(
                DbHelper.TABLE_TODO,
                DbHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(todoId)}
        );
    }

    public Cursor getAllTodos() {
        String[] projection = {
                DbHelper.COLUMN_ID + " as _id",  // Alias COLUMN_ID as _id
                DbHelper.COLUMN_TITLE,
                DbHelper.COLUMN_CONTENT,
                DbHelper.COLUMN_DATE,
                DbHelper.COLUMN_TYPE
        };

        return database.query(
                DbHelper.TABLE_TODO,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }
}
