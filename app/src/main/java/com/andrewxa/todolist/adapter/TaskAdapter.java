package com.andrewxa.todolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrewxa.todolist.R;
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.utils.myOnClickListener;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private Context context;
    private List<Task> tasks;
    private myOnClickListener myOnClickListener;


    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;

    }


    public void setMyOnClickListener(com.andrewxa.todolist.utils.myOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
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
    }


    public void update(List<Task> newTasks) {
        this.tasks = newTasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button editButton, removeButton, confirmButton;
        private TextView nameView;
        private EditText editText;

        ViewHolder(View view){
            super(view);
            editButton = (Button) view.findViewById(R.id.editButton);
            confirmButton = (Button) view.findViewById(R.id.confirmButton);
            removeButton = (Button) view.findViewById(R.id.removeButton);
            nameView = (TextView) view.findViewById(R.id.nameView);
            editText = (EditText) view.findViewById(R.id.editView);

            editButton.setOnClickListener(this);
            confirmButton.setOnClickListener(this);
            removeButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            long id = tasks.get(getAdapterPosition()).getId();
            String oldTask = nameView.getText().toString();

            if(view.getId() == editButton.getId()) {

                editButton.setVisibility(View.GONE);
                confirmButton.setVisibility(View.VISIBLE);
                nameView.setVisibility(View.GONE);
                editText.setVisibility(View.VISIBLE);
                editText.requestFocus();

            } else if(view.getId() == confirmButton.getId()) {

                if(editText.getText().toString().length() <1 ) {
                    editText.setError("Task should be minimum 1 symbol");
                    editText.requestFocus();
                } else {
                    confirmButton.setVisibility(View.GONE);
                    nameView.setText(editText.getText().toString());
                    nameView.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.GONE);
                    myOnClickListener.onConfirmClick(id, editText.getText().toString());
                    editButton.setVisibility(View.VISIBLE);
                }

            } else if(view.getId() == removeButton.getId()) {
                myOnClickListener.onRemoveClick(id);
            }

        }
    }
}


