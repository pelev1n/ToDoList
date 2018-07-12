package com.andrewxa.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrewxa.todolist.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private List<Task> tasks;

    TaskAdapter(Context context, List<Task> tasks) {
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.nameView.setText(task.getName());
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


