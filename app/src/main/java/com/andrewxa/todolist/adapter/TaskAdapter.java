package com.andrewxa.todolist.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrewxa.todolist.R;
import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.data.sqlite.SqliteTable;
import com.andrewxa.todolist.presenter.Presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private Context context;
    private List<Task> tasks;
    private Presenter presenter;
    Contract.IView view;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
        presenter = new Presenter((Contract.IView) context,context);

    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Task task = tasks.get(position);
        holder.nameView.setText(task.getName());
        final long id = task.getId();

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.editText.setVisibility(View.VISIBLE);
                holder.editText.requestFocus();
                holder.nameView.setVisibility(View.GONE);
                holder.editButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        holder.nameView.setVisibility(View.VISIBLE);
                        holder.nameView.setText(holder.editText.getText().toString());
                        String newTask = holder.editText.getText().toString();



                        if(!presenter.onCheckTaskClicked(newTask)) {
                            presenter.incorectTask();
                            return;
                        }
                        presenter.onEditTaskButtonClicked(newTask,id);
                        holder.editText.setVisibility(View.GONE);
                    }
                });

            }
        });


        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDeleteTaskButtonClicked(id);
            }
        });
    }

    public void update(List<Task> newTasks) {
        this.tasks = newTasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button editButton, removeButton;
        private TextView nameView;
        private EditText editText;

        ViewHolder(View view){
            super(view);
            editButton = (Button) view.findViewById(R.id.editButton);
            removeButton = (Button) view.findViewById(R.id.removeButton);
            nameView = (TextView) view.findViewById(R.id.nameView);
            editText = (EditText) view.findViewById(R.id.editView);
        }




    }
}


