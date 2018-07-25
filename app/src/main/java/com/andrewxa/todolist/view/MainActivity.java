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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.view {

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


        final Presenter presenter = new Presenter(this,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TaskAdapter(presenter.getAllTask());
        recyclerView.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        presenter.addTask(itemET.getText().toString());
                    }
                }).start();
                itemET.setText("");
            }
        });

        adapter.setMyOnClickListener(new myOnClickListener() {
            @Override
            public void onConfirmClick(final long id, final String newTask) {

                new Thread(new Runnable() {
                    public void run() {
                        presenter.editTask(newTask,id);
                    }
                }).start();
            }

            @Override
            public void onRemoveClick(final long id) {
                new Thread(new Runnable() {
                    public void run() {
                        presenter.deleteTask(id);
                    }
                }).start();
            }
        });

    }

    @Override
    public void updateData(List<Task> tasks) {
        adapter.update(tasks);
    }



    @Override
    public void message(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
