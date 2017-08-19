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
import static android.R.attr.name;

public class EditItemActivity extends AppCompatActivity {


    private final int REQUEST_CODE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        String bodyText = getIntent().getExtras().getString("bodyText");
        String tags = getIntent().getExtras().getString("tags");

        final int position = getIntent().getExtras().getInt("position", 0);
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        final EditText editText = (EditText) findViewById(R.id.editBodyText);
        final EditText tagText = (EditText) findViewById(R.id.tagText);

        editText.setText(bodyText);
        tagText.setText(tags);


        final EditItemActivity self = this;

        saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent data = new Intent();
                        // Pass relevant data back as a result
                        data.putExtra("bodyText",   editText.getText().toString() );
                        data.putExtra("tags",   tagText.getText().toString() );


                        data.putExtra("position", position); // ints work too
                        // Activity finished ok, return the data
                        setResult(RESULT_OK, data);

                        self.setResult(RESULT_OK, data);
                        self.finish();
                    }
        });
    }


}
