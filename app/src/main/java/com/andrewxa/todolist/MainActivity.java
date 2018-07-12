package com.andrewxa.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andrewxa.todolist.model.Task;
import com.andrewxa.todolist.utils.myOnClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText itemET;
    private Button btn;
    private List<Task> tasks = new ArrayList();
    private TaskAdapter2 adapter2;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);

        tasks.add(new Task("Read Book"));
        tasks.add(new Task("Swim"));
        tasks.add(new Task("Test 3"));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter2 = new TaskAdapter2(tasks);
        recyclerView.setAdapter(adapter2);

        /*

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        final TaskAdapter adapter = new TaskAdapter(this, tasks);
        recyclerView.setAdapter(adapter);
*/

        adapter2.setMyOnClickListener(new myOnClickListener() {
            @Override
            public void onItemClick(View view, int position, String passString) {
                adapter2.editItem(new Task(passString), position);
                Toast.makeText(MainActivity.this, "check:" + passString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSimpleClick(int position) {
                Toast.makeText(MainActivity.this, "pos simple Click: " + position, Toast.LENGTH_SHORT).show();
            }
        });


        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_btn:
                        String itemEntered = itemET.getText().toString();
                        adapter.addTask(new Task(itemEntered));
                        itemET.setText("");
                        break;
                }
            }
        });*/
    }
}
