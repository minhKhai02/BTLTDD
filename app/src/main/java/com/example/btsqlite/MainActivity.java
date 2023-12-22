package com.example.btsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TodoDao todoDao;
    private ToDoAdapter todoAdapter;
    private ToDo selectedTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoDao = new TodoDao(this);
        todoDao.open();

        todoAdapter = new ToDoAdapter(this, todoDao.getAllTodos());

        ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(todoAdapter);

        Button addButton = findViewById(R.id.button2);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextTitle = findViewById(R.id.editTextText9);
                EditText editTextContent = findViewById(R.id.editTextText10);
                EditText editTextDate = findViewById(R.id.editTextText);
                EditText editTextType = findViewById(R.id.editTextText11);

                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();
                String date = editTextDate.getText().toString();
                String type = editTextType.getText().toString();

                ToDo newTodo = new ToDo();
                newTodo.setTitle(title);
                newTodo.setContent(content);
                newTodo.setDate(date);
                newTodo.setType(type);

                long id = todoDao.insert(newTodo);

                if (id != -1) {
                    Toast.makeText(MainActivity.this, "Todo added successfully", Toast.LENGTH_SHORT).show();
                    todoAdapter.swapCursor(todoDao.getAllTodos());
                } else {
                    Toast.makeText(MainActivity.this, "Error adding todo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button updateButton = findViewById(R.id.button3);
        updateButton.setOnClickListener(new View.OnClickListener() {


            @Override


            public void onClick(View v) {
                if (selectedTodo != null) {


                    EditText editTextTitle = findViewById(R.id.editTextText9);


                    EditText editTextContent = findViewById(R.id.editTextText10);


                    EditText editTextDate = findViewById(R.id.editTextText);


                    EditText editTextType = findViewById(R.id.editTextText11);


                    String title = editTextTitle.getText().toString();


                    String content = editTextContent.getText().toString();


                    String date = editTextDate.getText().toString();
                    String type = editTextType.getText().toString();

                    selectedTodo.setTitle(title);
                    selectedTodo.setContent(content);
                    selectedTodo.setDate(date);
                    selectedTodo.setType(type);

                    int rowsAffected = todoDao.update(selectedTodo);

                    if (rowsAffected > 0) {
                        Toast.makeText(MainActivity.this, "Todo updated successfully", Toast.LENGTH_SHORT).show();
                        todoAdapter.swapCursor(todoDao.getAllTodos());
                        clearEditTextFields();
                    } else {
                        Toast.makeText(MainActivity.this, "Error updating todo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please select a todo to update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button deleteButton = findViewById(R.id.button4);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTodo != null) {
                    long todoIdToDelete = selectedTodo.getId();
                    int rowsAffected = todoDao.delete(todoIdToDelete);

                    if (rowsAffected > 0) {
                        Toast.makeText(MainActivity.this, "Todo deleted successfully", Toast.LENGTH_SHORT).show();
                        todoAdapter.swapCursor(todoDao.getAllTodos());
                        clearEditTextFields();
                    } else {
                        Toast.makeText(MainActivity.this, "Error deleting todo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please select a todo to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void clearEditTextFields() {
        EditText editTextTitle = findViewById(R.id.editTextText9);
        EditText editTextContent = findViewById(R.id.editTextText10);
        EditText editTextDate = findViewById(R.id.editTextText);
        EditText editTextType = findViewById(R.id.editTextText11);

        editTextTitle.setText("");
        editTextContent.setText("");
        editTextDate.setText("");
        editTextType.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        todoDao.close();
    }
}