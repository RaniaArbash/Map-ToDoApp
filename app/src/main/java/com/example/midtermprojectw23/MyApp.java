package com.example.midtermprojectw23;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

public class MyApp extends Application {

    ArrayList<Task> taskArrayList = new ArrayList<Task>(Arrays.asList(
          new Task("Fix the door","Feb 23, 2023", true),
        new Task("get the books","Feb 26, 2023", false),
         new Task("cook the meal","Feb 21, 2023", false),
       new Task("go shopping","March 1, 2023", true)


            ));
}





