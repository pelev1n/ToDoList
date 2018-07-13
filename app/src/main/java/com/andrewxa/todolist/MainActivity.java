package com.andrewxa.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andrewxa.todolist.db.TaskDbHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private TaskAdapter mAdapter;

    private EditText itemET;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TaskDbHelper dbHelper = new TaskDbHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TaskAdapter(this,getAllItems());
        recyclerView.setAdapter(mAdapter);

        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemEntered = itemET.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put(TaskDbHelper.COLUMN_NAME,itemEntered);
                mDatabase.insert(TaskDbHelper.TABLE,null,cv);
                mAdapter.swapCursor(getAllItems());
                itemET.setText("");
            }
        });
    }
    private Cursor getAllItems() {
        return mDatabase.query(
                TaskDbHelper.TABLE,
                null,
                null,
                null,
                null,
                null,
                TaskDbHelper.COLUMN_ID + " DESC"
        );
    }
}
