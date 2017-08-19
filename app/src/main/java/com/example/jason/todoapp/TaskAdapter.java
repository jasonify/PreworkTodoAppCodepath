package com.example.jason.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by jason on 8/15/17.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
     super(context, 0, tasks);
    }


    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        Task task = getItem(position);
        if ( contentView == null ) {
            contentView = LayoutInflater.from(getContext()).inflate(R.layout.item_ask, parent, false);
        }

        TextView tvTitle = (TextView) contentView.findViewById(R.id.tvTitle);
        TextView tvTags = (TextView) contentView.findViewById(R.id.tvTags);


        tvTitle.setText(task.title);
        tvTags.setText(task.tags);

        return contentView;
    }
}
