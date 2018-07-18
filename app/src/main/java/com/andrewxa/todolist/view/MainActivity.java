package com.andrewxa.todolist.view;

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
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.presenter.Presenter;
import com.andrewxa.todolist.utils.myOnClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.IView {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private EditText itemET;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);


        final Presenter presenter = new Presenter(this, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(this, presenter.getAllTaskClicked());
        recyclerView.setAdapter(adapter);


        adapter.setMyOnClickListener(new myOnClickListener() {
            @Override
            public void onItemClick(View view, int position, String passString) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSimpleClick(int position) {
                Toast.makeText(MainActivity.this, "click 2", Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddTaskButtonClicked(itemET.getText().toString());
                itemET.setText("");
            }
        });


    }


    @Override
    public void updateData(List<Task> tasks) {
        adapter.update(tasks);
    }


    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
