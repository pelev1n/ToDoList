package com.andrewxa.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrewxa.todolist.model.Task;

import java.util.ArrayList;

public class TaskAdapterv1 extends ArrayAdapter<Task> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Task> taskList;

    TaskAdapterv1(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.taskList = tasks;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Task task = taskList.get(position);

        viewHolder.nameView.setText(task.getName());

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.editText.setVisibility(View.VISIBLE);
                viewHolder.editText.requestFocus();
                viewHolder.nameView.setVisibility(View.GONE);
                final String oldTask = viewHolder.nameView.getText().toString();

                viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewHolder.nameView.setVisibility(View.VISIBLE);
                        /*                        String newTask = viewHolder.editText.getText().toString();*/
                        viewHolder.nameView.setText(viewHolder.editText.getText().toString());

                        for( Task task : taskList) {
                            if(task.getName() == oldTask)
                                task.setName(viewHolder.editText.getText().toString());
                        }
                        notifyDataSetChanged();
                        viewHolder.editText.setVisibility(View.GONE);
                    }
                });

            }
        });


        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private Button editButton, removeButton;
        private TextView nameView;
        private EditText editText;

        ViewHolder(View view){
            editButton = (Button) view.findViewById(R.id.editButton);
            removeButton = (Button) view.findViewById(R.id.removeButton);
            nameView = (TextView) view.findViewById(R.id.nameView);
            editText = (EditText) view.findViewById(R.id.editView);
        }
    }
}
