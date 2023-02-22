package com.example.midtermprojectw23;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TasksBaseAdapter extends BaseAdapter {

    ArrayList<Task> listOfTasks;
    Context context;

    public TasksBaseAdapter(ArrayList<Task> listOfTasks, Context context) {
        this.listOfTasks = listOfTasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listOfTasks.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_row,viewGroup,false);

        TextView todo = v.findViewById(R.id.todo_text);
        TextView date = v.findViewById(R.id.todo_date);

        todo.setText(listOfTasks.get(i).task);
        date.setText(listOfTasks.get(i).date);

        return  v;
    }
}
