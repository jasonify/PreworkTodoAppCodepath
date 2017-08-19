package com.example.jason.todoapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 8/15/17.
 */

public class Task {

    public String title;

    public String tags;

    public Task(String title, String tags) {
        this.title = title;
        this.tags = tags;

    }

//    public Task(String rawTaskString) {
//        this(rawTaskString.split("---")[0], rawTaskString.split("---")[1]);
//    }


    public static ArrayList<String> getStringTasks(ArrayList<Task> tasks) {
        ArrayList<String> taskStrings = new ArrayList<String>();
        for(Task task: tasks) {
            taskStrings.add(task.title + "---" + task.tags);
        }
        return  taskStrings;
    }
    public static ArrayList<Task> getTasks(List<String> rawTaskStrings) {

        ArrayList<Task> tasks = new ArrayList<Task>();

        for(String element: rawTaskStrings) {
            String[] elements = element.split("---");
            if (elements.length == 0) {

            } else if (elements.length == 1 ) {
                tasks.add(new Task(element.split("---")[0], "" ));
            } else {
                tasks.add(new Task(element.split("---")[0], element.split("---")[1]));
            }


        }
        return tasks;
    }
}
