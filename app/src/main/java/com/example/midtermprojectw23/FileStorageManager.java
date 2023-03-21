package com.example.midtermprojectw23;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileStorageManager {


    public static void deleteAllTodos(Context context){
        try (FileOutputStream fos =
                     context.openFileOutput("ToDos.txt", Context.MODE_PRIVATE)) {
             fos.write("".getBytes());

        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void writeNewTaskToTheFile(Context context, Task newTask){

        try (FileOutputStream fos =
                     context.openFileOutput("ToDos.txt", Context.MODE_APPEND)) {
           String isurgent = newTask.isUrgent? "1" : "0";

           // newTask = ["go shopping","March 20. 2023", true]
            //fileContent = "go shopping - March 20 2023-1"
            String fileContent = "$"+newTask.task + "-"+ newTask.date+"-"+ isurgent + "$";
            fos.write(fileContent.getBytes());

        }catch (IOException e){
            System.out.println(e);
        }
    }



    static ArrayList<Task> readAllToDos(Context context){
        ArrayList<Task> allTasks = new ArrayList<>(0);
        FileInputStream fis = null;
        try {
            fis = context.openFileInput("ToDos.txt");

        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            int read = 0;
          while((read = reader.read()) != -1 ) {// -1 = EOF
              char letter = (char) read;
              stringBuilder.append(letter);
          }
          String fileContent = stringBuilder.toString();
            allTasks = fromStringToToDoList(fileContent);
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allTasks;
    }

    private static ArrayList<Task> fromStringToToDoList(String fileContent){
        ArrayList<Task> arrayList = new ArrayList<>(0);
        int firstOfATask = 0;
        int endOfATask = 0;
        for (int i = 0 ; i< fileContent.length(); i++){
            if (fileContent.charAt(i) == '$'){
                for (int j = i+1 ; j < fileContent.length(); j++){
                    if (fileContent.charAt(j) == '$'){
                        firstOfATask = i;
                        endOfATask = j;
                        String onetask = fileContent.substring(i,j);
                        // from one string to one task
                        arrayList.add(Task.fromString(onetask));

                        firstOfATask = j + 1;
                        i = j;
                        endOfATask = 0;
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

}
