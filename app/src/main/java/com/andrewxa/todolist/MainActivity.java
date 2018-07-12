package com.andrewxa.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.andrewxa.todolist.model.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText itemET;
    private Button btn;
    ArrayList<Task> tasks = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);

        RecyclerView taskList = (RecyclerView) findViewById(R.id.list);
        final TaskAdapter adapter = new TaskAdapter(this, tasks);
        taskList.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_btn:
                        String itemEntered = itemET.getText().toString();
                        /*adapter.add(new Task(itemEntered));*/
                        tasks.add(new Task(itemEntered));
                        itemET.setText("");
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }
}
