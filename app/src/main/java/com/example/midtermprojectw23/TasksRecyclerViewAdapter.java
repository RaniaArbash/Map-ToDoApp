package com.example.midtermprojectw23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TasksRecyclerViewAdapter
        extends RecyclerView.Adapter<TasksRecyclerViewAdapter.TasksViewHolder>
{

    interface ToDoAdapterClicCallBack{// to provided any listener with notification
        //when one item clicked
        void todoClicked(int index);
    }

    ArrayList<Task> taskArrayList;
    Context context;
    ToDoAdapterClicCallBack listener;

    TasksRecyclerViewAdapter(ArrayList<Task> list, Context c){
        taskArrayList = list;
        context = c;
    }

    class TasksViewHolder extends RecyclerView.ViewHolder
            {
        TextView tasktextView;
        TextView dateTextView;
        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            tasktextView = (TextView) itemView.findViewById(R.id.todo_text);
            dateTextView = (TextView) itemView.findViewById(R.id.todo_date);
        }
    }
    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.task_row, parent, false);

        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {

        if (taskArrayList.get(position).isUrgent){
            holder.tasktextView.setTextColor(context.getColor(R.color.red));
            holder.dateTextView.setTextColor(context.getColor(R.color.red));
        }else {
            holder.tasktextView.setTextColor(context.getColor(R.color.green));
            holder.dateTextView.setTextColor(context.getColor(R.color.green));
        }
        holder.tasktextView.setText(taskArrayList.get(position).task);
        holder.dateTextView.setText(taskArrayList.get(position).date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.todoClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
