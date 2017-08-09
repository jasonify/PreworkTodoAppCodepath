package com.example.jason.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.data;

public class EditItemActivity extends AppCompatActivity {


    private final int REQUEST_CODE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */


        //@Override
        // protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        Log.d("tag me " , "something here");

        // if (resultCode == RESULT_OK ) {
        // Extract name value from result extras
        String name = getIntent().getExtras().getString("bodyText");

        final int position = getIntent().getExtras().getInt("index", 0);
        // Toast the name to display temporarily on screen
        // Toast.makeText(this, name, Toast.LENGTH_SHORT).show();


        Button saveButton = (Button) findViewById(R.id.buttonSave);
        final EditText editText = (EditText) findViewById(R.id.editText4);
        editText.setText(name);


        final EditItemActivity self = this;

        saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent data = new Intent();
                        // Pass relevant data back as a result
                        data.putExtra("bodyText",   editText.getText() );
                        data.putExtra("index", position); // ints work too
                        // Activity finished ok, return the data
                        setResult(RESULT_OK, data);


                        self.finish();
                    }
        });




    }


}
