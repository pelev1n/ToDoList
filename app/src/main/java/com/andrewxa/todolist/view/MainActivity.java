package com.andrewxa.todolist.view;

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
import android.widget.Toast;

import com.andrewxa.todolist.R;
import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.adapter.TaskAdapter;
import com.andrewxa.todolist.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements Contract.IView{

    private RecyclerView recyclerView;
    private TaskAdapter mAdapter;
    private EditText itemET;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);

        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);


        final Presenter presenter = new Presenter(this,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TaskAdapter(this,presenter.onGetAllTaskClicked());
        recyclerView.setAdapter(mAdapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddTaskButtonClicked(itemET.getText().toString());
                itemET.setText("");
            }
        });
    }

  public void updateData(Cursor cursor) {
      mAdapter.update(cursor);
  }
    @Override
    public void message(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
