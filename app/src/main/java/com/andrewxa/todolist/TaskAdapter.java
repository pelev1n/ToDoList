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
    private List<Task> tasksList;

    TaskAdapter(Context context, List<Task> tasks) {
        this.tasksList = tasks;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Task task = tasksList.get(position);
        holder.nameView.setText(task.getName());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.editText.setVisibility(View.VISIBLE);
                holder.editText.requestFocus();
                holder.nameView.setVisibility(View.GONE);
                final String oldTask = holder.nameView.getText().toString();

                holder.editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.nameView.setVisibility(View.VISIBLE);
                        /*                        String newTask = viewHolder.editText.getText().toString();*/
                        holder.nameView.setText(holder.editText.getText().toString());

                        for( Task task : tasksList) {
                            if(task.getName() == oldTask)
                                task.setName(holder.editText.getText().toString());
                        }
                        notifyDataSetChanged();
                        holder.editText.setVisibility(View.GONE);
                    }
                });

            }
        });


        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasksList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public void addTask(Task task) {
        tasksList.add(task);
        notifyDataSetChanged();
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


