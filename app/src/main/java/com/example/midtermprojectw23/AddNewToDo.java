package com.example.midtermprojectw23;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.os.Bundle;

public class AddNewToDo extends AppCompatActivity {

        DatePicker picker;
        EditText taskText;
        Switch isargent;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_new_to_do);
            picker = findViewById(R.id.datapicker);
            taskText = findViewById(R.id.task_id);
            isargent = findViewById(R.id.isargent_switch);

        }

        public void save_task(View view) {
            boolean isa =  isargent.isChecked()? true: false;
            String taskDate = picker.getDayOfMonth()+"/"+picker.getMonth()+"/"+picker.getYear();
            if (!taskText.getText().toString().isEmpty()){
                Task newTodo = new Task(taskText.getText().toString(), taskDate,isa);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("newTodo",newTodo);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }
    }
