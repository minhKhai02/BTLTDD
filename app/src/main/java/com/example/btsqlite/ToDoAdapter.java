package com.example.btsqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

public class ToDoAdapter extends CursorAdapter {

    public ToDoAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView contentTextView = view.findViewById(R.id.contentTextView);
        TextView dateTextView = view.findViewById(R.id.dateTextView);
        TextView typeTextView = view.findViewById(R.id.typeTextView);

        // Extract data from the cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TITLE));
        String content = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CONTENT));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_DATE));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TYPE));

        // Populate views with data
        titleTextView.setText(title);
        contentTextView.setText(content);
        dateTextView.setText(date);
        typeTextView.setText(type);
    }
}