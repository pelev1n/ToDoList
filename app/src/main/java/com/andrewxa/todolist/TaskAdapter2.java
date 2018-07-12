package com.andrewxa.todolist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrewxa.todolist.model.Task;
import com.andrewxa.todolist.utils.myOnClickListener;

import java.util.List;

public class TaskAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private myOnClickListener myOnClickListener;
    private List<Task> taskList;

    public TaskAdapter2(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ListVH listVH = (ListVH) viewHolder;
        listVH.tvName.setText(taskList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void editItem(Task task, int position) {
        taskList.set(position, task);
        notifyDataSetChanged();
    }

    class ListVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button btnEdit, btnRemove, btnConfirm;
        private TextView tvName;
        private EditText etInput;

        public ListVH(@NonNull View itemView) {
            super(itemView);
            btnEdit = itemView.findViewById(R.id.editButton);
            btnRemove = itemView.findViewById(R.id.removeButton);
            btnConfirm = itemView.findViewById(R.id.btnConfirm);
            tvName = itemView.findViewById(R.id.nameView);
            etInput = itemView.findViewById(R.id.editView);
            btnRemove.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
            btnConfirm.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == btnRemove.getId()) {
                myOnClickListener.onSimpleClick(getAdapterPosition());
            } else if (view.getId() == btnEdit.getId()) {
                myOnClickListener.onSimpleClick(getAdapterPosition());
                btnEdit.setVisibility(View.GONE);
                btnConfirm.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.GONE);
                etInput.setVisibility(View.VISIBLE);
            } else if (view.getId() == btnConfirm.getId()) {
                btnConfirm.setVisibility(View.GONE);
                etInput.setVisibility(View.GONE);
                tvName.setVisibility(View.VISIBLE);
                myOnClickListener.onItemClick(view, getAdapterPosition(), etInput.getText().toString());
                btnEdit.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setMyOnClickListener(com.andrewxa.todolist.utils.myOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }
}
