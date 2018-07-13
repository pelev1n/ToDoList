package com.andrewxa.todolist;

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

import com.andrewxa.todolist.db.TaskDbHelper;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    SQLiteDatabase mDatabase;
    private Context context;
    private Cursor cursor;

    public TaskAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;

    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(!cursor.moveToPosition(position)) {
            return;
        }
        String name = cursor.getString(cursor.getColumnIndex(TaskDbHelper.COLUMN_NAME));
        final long id = cursor.getLong(cursor.getColumnIndex(TaskDbHelper.COLUMN_ID));

        holder.nameView.setText(name);

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
                        holder.nameView.setText(holder.editText.getText().toString());

                        ContentValues cv = new ContentValues();
                        cv.put(TaskDbHelper.COLUMN_NAME,holder.editText.getText().toString());
                        TaskDbHelper taskDbHelper = new TaskDbHelper(context);
                        mDatabase = taskDbHelper.getWritableDatabase();
                        mDatabase.insert(TaskDbHelper.TABLE,null,cv);

                        remove(id);
                        holder.editText.setVisibility(View.GONE);
                    }
                });

            }
        });


        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if(cursor != null) {
            cursor.close();
        }
        cursor = newCursor;

        if(newCursor != null) {
            notifyDataSetChanged();
        }
    }

    private void remove(long id) {
        TaskDbHelper taskDbHelper = new TaskDbHelper(context);
        mDatabase = taskDbHelper.getWritableDatabase();
        mDatabase.delete(TaskDbHelper.TABLE,TaskDbHelper.COLUMN_ID+"="+id,null);
        swapCursor(getAllItems());
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                TaskDbHelper.TABLE,
                null,
                null,
                null,
                null,
                null,
                TaskDbHelper.COLUMN_ID + " DESC"
        );
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


