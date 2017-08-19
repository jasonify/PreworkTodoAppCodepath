package com.example.jason.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.data;
import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> todoItems;
    TaskAdapter aTodoAdapter;
    ListView lvItems;
    EditText etEditText;

    private final int REQUEST_CODE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aTodoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);



        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("item position", Integer.toString(position));
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("position",  position);
                i.putExtra("bodyText", todoItems.get(position).title);
                i.putExtra("tags", todoItems.get(position).tags);

                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK  && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
             String bodyText = data.getExtras().getString("bodyText");
            String tags = data.getExtras().getString("tags");
             int index = data.getExtras().getInt("position", 0);
             // Toast the name to display temporarily on screen
            todoItems.set(index, new Task(bodyText, tags));
            aTodoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            todoItems =   Task.getTasks(FileUtils.readLines(file));  // new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {
            Log.d("io exception", e.getLocalizedMessage());
            todoItems = new ArrayList<Task>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(file, Task.getStringTasks(todoItems));
        } catch (IOException e) {

        }
    }

    public void populateArrayItems() {
        readItems();
//        Task task = new Task( );
        aTodoAdapter = new TaskAdapter(this, todoItems);
    }

    public void onAddItem(View view) {
        System.out.println("hello item!");
        aTodoAdapter.add(new Task( etEditText.getText().toString() , "#some #tags #again" ));
        etEditText.setText("");
        writeItems();
    }



}
