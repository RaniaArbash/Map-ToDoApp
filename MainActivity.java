package com.example.midtermprojectw23;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        TasksRecyclerViewAdapter.ToDoAdapterClicCallBack,
        AddNewTodoDialogFragment.DialogFragmentCallBackInterface
{

    ListView list;
    FragmentManager fragmentManager;
    RecyclerView recyclerList;
    ArrayList<Task> tasks;
    ActivityResultLauncher<Intent> todoResultLauncher;
    TasksRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks = ((MyApp)getApplication()).taskArrayList;
        tasks = FileStorageManager.readAllToDos(this);
        ((MyApp)getApplication()).taskArrayList = tasks;
        recyclerList = findViewById(R.id.recycler_list);
        fragmentManager = getSupportFragmentManager();

//        list = findViewById(R.id.listoftasks);
   //     TasksBaseAdapter baseadapter = new TasksBaseAdapter(tasks,this);
//        list.setAdapter(adapter);


        adapter = new TasksRecyclerViewAdapter(tasks, this);
        recyclerList.setAdapter(adapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        adapter.listener = this;

        todoResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Task newToDo = data.getParcelableExtra("newTodo");
                            tasks.add(newToDo);
                            FileStorageManager.writeNewTaskToTheFile(MainActivity.this, newToDo);
                            tasks = FileStorageManager.readAllToDos(MainActivity.this);
                            ((MyApp)getApplication()).taskArrayList = tasks;
                            adapter.taskArrayList = tasks;
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.addNewTodoID:{
                Intent i = new Intent(this, AddNewToDo.class);
             //   startActivity(i);
                todoResultLauncher.launch(i);
                break;
            }
            case R.id.addDialogFragment: {
                AddNewTodoDialogFragment dialgo =  new AddNewTodoDialogFragment();
                dialgo.listener = this;
                dialgo.show(getSupportFragmentManager().beginTransaction(),AddNewTodoDialogFragment.TAG);

            break;

            }
            case R.id.deleteAllToDos:{
                FileStorageManager.deleteAllTodos(this);
                tasks = FileStorageManager.readAllToDos(MainActivity.this);
                ((MyApp)getApplication()).taskArrayList = tasks;
                adapter.taskArrayList = tasks;
                adapter.notifyDataSetChanged();

                break;
            }
            case R.id.closeID:{
                finish();
                break;
            }
        }
        return true;
    }

    @Override
    public void todoClicked(int index) {
        Toast.makeText(this, getString(R.string.toastMsg)
                + tasks.get(index).task, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addingNewTaskListener(Task todo) {
        tasks.add(todo);
        FileStorageManager.writeNewTaskToTheFile(this, todo);
        adapter.notifyDataSetChanged();
    }
}
