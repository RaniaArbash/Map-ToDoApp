package com.example.midtermprojectw23;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasks = ((MyApp)getApplication()).taskArrayList;
        list = findViewById(R.id.listoftasks);
        TasksBaseAdapter adapter = new TasksBaseAdapter(tasks,this);
        list.setAdapter(adapter);

    }


}